package com.wizeline.DTO;

import java.time.LocalDateTime;
import java.util.Map;

public class BankAccountDTO {

    private long accountNumber;
    private String accountName;
    private String user;
    private double accountBalance;
    private AccountType accountType;
    private String country;
    private boolean accountActive;
    private LocalDateTime creationDate;
    private String lastUsage;

    public long getAccountNumber() {return accountNumber;}
    public String getAccountName() {return accountName;}
    public String getUser() {return user;}
    public double getAccountBalance() {return accountBalance;}
    public AccountType getAccountType() {return accountType;}
    public String getCountry() {return country;}
    public boolean getAccountActive() {return accountActive;}
    public LocalDateTime getCreationDate() { return creationDate; }
    public String getlastUsage() {return String.valueOf(LocalDateTime.now());}

    public void setAccountNumber(long accountNumber) {this.accountNumber = accountNumber;}
    public void setAccountName(String accountName) {this.accountName = accountName;}
    public void setUser(String user) {this.user = user;}
    public void setAccountBalance(double accountBalance) {this.accountBalance = accountBalance;}
    public void setAccountType(AccountType accountType) {this.accountType = accountType;}
    public void setCountry(String country) {this.country = country;}
    public void setAccountActive(boolean accountActive) {this.accountActive = accountActive;}
    public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}
    public void setLastUsage(String lastUsage) {this.lastUsage = lastUsage;}


    public BankAccountDTO getAccountDetails(Map<String,String> bankParams) {
        BankAccountDTO bank = new BankAccountDTO();
        bank.setAccountNumber(Long.parseLong(bankParams.get("accountNumber")));
        bank.setAccountName(bankParams.get("accountName"));
        bank.setUser(bankParams.get("user"));
        bank.setAccountBalance(Double.parseDouble(bankParams.get("accountBalance")));
        bank.setAccountType(AccountType.valueOf(bankParams.get("accountType")));
        bank.setCountry(bankParams.get("country"));
        bank.setCreationDate(LocalDateTime.parse(bankParams.get("creationDate")));
        bank.setAccountActive(Boolean.parseBoolean(bankParams.get("accountActive")));
        bank.setLastUsage(bankParams.get("lastUsage"));
        return bank;
    }


}
