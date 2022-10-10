package com.wizeline.maven.learningjavamaven.service;

import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;

import java.util.List;

public interface BankAccountService {

    List<BankAccountDTO> getAccounts();
    void deleteAccounts();
    List<BankAccountDTO> getAccountByUser(String user);
}