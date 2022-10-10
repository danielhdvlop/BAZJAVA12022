package com.wizeline.maven.learningjavamaven.service;

import com.jayway.jsonpath.Criteria;
import com.wizeline.maven.learningjavamaven.controller.UserController;
import com.wizeline.maven.learningjavamaven.enums.Country;
import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;
import com.wizeline.maven.learningjavamaven.repository.BankingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.wizeline.maven.learningjavamaven.utils.Utils.*;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    public BankAccountDTO getAccountDetails(String user, boolean isActive, Country country, LocalDateTime lastUsage) {
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate usage = LocalDate.from(lastUsage);
        return buildBankAccount(user, true, Country.MX, usage.atStartOfDay());
    }
    private BankAccountDTO buildBankAccount(String user, boolean isActive, Country country, LocalDateTime lastUsage) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setAccountNumber(randomAcountNumber());
        bankAccountDTO.setAccountName("Dummy Account ".concat(randomInt()));
        bankAccountDTO.setUser(user);
        bankAccountDTO.setAccountBalance(randomBalance());
        bankAccountDTO.setAccountType(pickRandomAccountType());
        bankAccountDTO.setCountry(getCountry(country));
        bankAccountDTO.getlastUsage();
        bankAccountDTO.setCreationDate(lastUsage);
        bankAccountDTO.setAccountActive(isActive);
        return bankAccountDTO;
    }
    @Autowired
    private BankingAccountRepository bankAccountRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<BankAccountDTO> getAccounts() {
        List<BankAccountDTO> accountDTOList = new ArrayList<>();
        BankAccountDTO bankAccountOne = getAccountDetails("user3@wizeline.com", true, Country.MX, LocalDateTime.now().minusDays(7));
        accountDTOList.add(bankAccountOne);
        mongoTemplate.save(bankAccountOne);
        BankAccountDTO bankAccountTwo = buildBankAccount("user1@wizeline.com", false, Country.FR, LocalDateTime.now().minusMonths(2));
        accountDTOList.add(bankAccountTwo);
        mongoTemplate.save(bankAccountTwo);
        BankAccountDTO bankAccountThree = buildBankAccount("user2@wizeline.com" ,false, Country.US, LocalDateTime.now().minusYears(4));
        accountDTOList.add(bankAccountThree);
        mongoTemplate.save(bankAccountThree);
        mongoTemplate.findAll(BankAccountDTO.class).stream().map(bankAccountDTO -> bankAccountDTO.getUser()).forEach(
                (user) -> {
                    LOGGER.info("User stored in bankAccountCollection " + user );
                });
        return accountDTOList;
    }

    @Override
    public void deleteAccounts() {
        bankAccountRepository.deleteAll();
    }

    @Override
    public List<BankAccountDTO> getAccountByUser(String user) {
        Query query = new Query();
        query.addCriteria((CriteriaDefinition) Criteria.where("userName").is(user));
        return mongoTemplate.find(query, BankAccountDTO.class);
    }
}
