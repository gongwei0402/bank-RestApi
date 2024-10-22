package com.cogent.bank_rest_api.service;

import com.cogent.bank_rest_api.Entity.Account;

public interface AccountService {
    Account applyForAccount(Long userId, Account newAccount);
    Account approveAccount(Long userId, Long accountId, Account updateAccount);
    Double viewBalance(Long userId, Long accountId);
    void deposit(Long accountId, double amount, Account updateAccount);
    void withdraw(Long accountId, double amount, Account updateAccount);
    void moneyTransfer(Long accountId, Long fromAccountId, Long toAccountId, double amount, Account updateAccount);

}
