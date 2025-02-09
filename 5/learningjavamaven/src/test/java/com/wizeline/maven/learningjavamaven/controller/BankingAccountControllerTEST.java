package com.wizeline.maven.learningjavamaven.controller;

import com.jayway.jsonpath.Criteria;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.wizeline.maven.learningjavamaven.enums.AccountType;
import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;
import com.wizeline.maven.learningjavamaven.model.ResponseDTO;
import com.wizeline.maven.learningjavamaven.service.BankAccountService;
import com.wizeline.maven.learningjavamaven.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class BankingAccountControllerTEST {

    private static final Logger LOGGER = Logger.getLogger(BankingAccountControllerTEST.class.getName());

    int data = 1;
    private int codigoServ = 0;
    private String fecha = null;
    private String password = null;
    private String user = null;

    @Autowired
    private BankAccountService bankAccountService;

    @Mock
    private BankAccountDTO bankAccountDTO;

    @Mock
    ResponseEntity responseEntity;

    @Mock
    ResponseEntity<String> responseByType;

    @Mock
    ResponseEntity<List<BankAccountDTO>> responseList;

    @Mock
    ResponseDTO responseDTO;

    @Autowired
    private BankingAccountController bankingAccountController;

    @Autowired
    UserService userService;

    private MongoTemplate mongoTemplate;

    public BankingAccountControllerTEST(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @BeforeEach
    void antesPruebas() {
        data = 0;
        LOGGER.info("Antes de cada prueba " + data);
        if (user == null & password == null & fecha == null) {
            user = "daniel.vargaslo@elektra.com.mx";
            password = "Passwrd001";
            fecha = "20-12-2020";
        }
        bankAccountDTO.setUser("daniel.vargaslo@elektra.com");
        bankAccountDTO.setAccountType(AccountType.NOMINA);
        bankAccountDTO.setAccountActive(true);
        bankAccountDTO.setAccountBalance(98542.457);
        bankAccountDTO.setAccountName("Daniel H");
        bankAccountDTO.setAccountNumber(9823);
    }

    @Test
    @DisplayName("Prueba servicio Get /getUserAccount")
    public void pruebaUserAccount() {
        LOGGER.info("LearningJava - iniciando prueba getUserAccount");
        responseDTO = UserService.login(user, password);
        LOGGER.info("Recibiendo respuesta de login - " + responseDTO.getCode());
        responseEntity = bankingAccountController.getAccountByUser(user);
        Assertions.assertAll(
                () -> assertEquals("OK000", responseDTO.getCode()),
                () -> assertEquals(200, responseEntity.getStatusCodeValue())
        );
    }

    @Test
    @DisplayName("Prueba servicio Get /getAccountByUser")
    public void pruebaGetAccounts() {
        LOGGER.info("LearningJava - iniciando prueba getAccounts");
        responseList = bankingAccountController.getAccountByUser(user);
        assertEquals(200, responseList.getStatusCodeValue());
        LOGGER.info("Se obtiene el codigo de respuesta: " + responseList.getStatusCodeValue());
    }

    @Test
    @DisplayName("Prueba servicio getExternalUser")
    public void getExternalUser() {
        LOGGER.info("LearningJava - iniciando prueba getExternalUser");
        responseByType = bankingAccountController.getExternalUser(Long.valueOf(user));
        assertEquals(200, responseByType.getStatusCodeValue());
        LOGGER.info("Se obtiene el codigo de respuesta: " + responseByType.getStatusCodeValue());
    }

    @Test
    @DisplayName("Prueba de deleteAccounts")
    public void deleteAccounts() {
        LOGGER.info("LearningJava - iniciando prueba getExternalUser");
        responseByType = bankingAccountController.deleteAccounts();
        assertEquals(200, responseByType.getStatusCodeValue());
        LOGGER.info("Se obtiene el codigo de respuesta: " + responseByType.getStatusCodeValue());
    }

    @Test
    @DisplayName("Prueba de MongoBD SelectbyUserName")
    public void selectuseName() {
        LOGGER.info("LearningJava - iniciando prueba MongoBD (SELECT)");
        Query query = new Query();
        query.addCriteria((CriteriaDefinition) Criteria.where("userName").is(user));
        LOGGER.info("Procesando prueba MongoBD (SELECT)");
        responseByType = (ResponseEntity<String>) mongoTemplate.find(query, BankAccountDTO.class);
        assertEquals(200, responseByType.getStatusCodeValue());
        LOGGER.info("Se obtiene el codigo de respuesta: " + responseByType.getStatusCodeValue());
    }

    @Test
    @DisplayName("Prueba de MongoBD SelectAll")
    public void selectAll() {
        LOGGER.info("LearningJava - iniciando prueba MongoBD (SELECT)");
        Query query = new Query();
        query.addCriteria((CriteriaDefinition) Criteria.where("1").is(1));
        LOGGER.info("Procesando prueba MongoBD (SELECT)");
        responseByType = (ResponseEntity<String>) mongoTemplate.find(query, BankAccountDTO.class);
        assertEquals(200, responseByType.getStatusCodeValue());
        LOGGER.info("Se obtiene el codigo de respuesta: " + responseByType.getStatusCodeValue());
    }

    @Test
    @DisplayName("Prueba de MongoBD DELETE")
    public void deleteAccounts2() {
        DeleteResult deleteReponse;
        LOGGER.info("LearningJava - iniciando prueba MongoBD (DELETE)");
        Query query = new Query();
        query.addCriteria((CriteriaDefinition) Criteria.where("1").is(1));
        LOGGER.info("LProcesando prueba MongoBD (SELECT)");
        deleteReponse = mongoTemplate.remove(new Query(), "users");
        assertEquals(200, deleteReponse.getDeletedCount());
        LOGGER.info("Se obtiene el codigo de respuesta: " + responseByType.getStatusCodeValue());
    }
    @Test
    @DisplayName("Prueba de MongoBD UPDATE")
    public void update() {
        LOGGER.info("LearningJava - iniciando prueba MongoBD (UPDATE)");
        Query query = new Query();
        query.addCriteria((CriteriaDefinition) Criteria.where("userName").is(user));
        LOGGER.info("Procesando prueba MongoBD (UPDATE)");
        UpdateResult updateResponse = mongoTemplate.updateFirst(query, Update.update("message", "This comment updated by updateFirst methods"), Comment.class);
        assertEquals(200, updateResponse.getModifiedCount());
        LOGGER.info("Se obtiene el codigo de respuesta: " + responseByType.getStatusCodeValue());
    }
}