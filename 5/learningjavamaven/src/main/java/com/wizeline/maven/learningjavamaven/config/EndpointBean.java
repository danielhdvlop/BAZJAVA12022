package com.wizeline.maven.learningjavamaven.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
@Component
@Validated
@ConfigurationProperties(prefix = "consumers")
public class EndpointBean {
    @NotNull
    private String login;
    @NotNull
    private String createUser;
    @NotNull
    private String updateUser;
    @NotNull
    private String createUsers;
    @NotNull
    private String userAccount;
    @NotNull
    private String accounts;
    @NotNull
    private String accountsGroupByType;
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getCreateUser() {
        return createUser;
    }
    public String getUpdateUser() {
        return updateUser;
    }
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    public String getCreateUsers() {
        return createUsers;
    }
    public void setCreateUsers(String createUsers) {
        this.createUsers = createUsers;
    }
    public String getUserAccount() {
        return userAccount;
    }
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
    public String getAccounts() {
        return accounts;
    }
    public void setAccounts(String accounts) {
        this.accounts = accounts;
    }
    public String getAccountsGroupByType() {
        return accountsGroupByType;
    }
    public void setAccountsGroupByType(String accountsGroupByType) {
        this.accountsGroupByType = accountsGroupByType;
    }
}