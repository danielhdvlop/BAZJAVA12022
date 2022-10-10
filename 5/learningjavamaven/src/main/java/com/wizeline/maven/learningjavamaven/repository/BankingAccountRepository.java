package com.wizeline.maven.learningjavamaven.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;

@Repository
public interface BankingAccountRepository extends MongoRepository<BankAccountDTO, Long> {

}
