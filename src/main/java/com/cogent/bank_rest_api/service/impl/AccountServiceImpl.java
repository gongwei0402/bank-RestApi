package com.cogent.bank_rest_api.service.impl;

import com.cogent.bank_rest_api.Entity.Account;
import com.cogent.bank_rest_api.Entity.User;
import com.cogent.bank_rest_api.exception.BlogApiException;
import com.cogent.bank_rest_api.exception.ResourceNotFoundException;
import com.cogent.bank_rest_api.repository.AccountRepository;
import com.cogent.bank_rest_api.repository.UserRepository;
import com.cogent.bank_rest_api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Account applyForAccount(Long userId, Account newAccount) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("users","userId",userId));
        newAccount.setUser(user);
        newAccount.setApproved(false);
        return accountRepository.save(newAccount);
    }

    @Override
    public Account approveAccount(Long userId, Long accountId, Account updateAccount) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("users","userId",userId));
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(()-> new ResourceNotFoundException("accounts","accountId",accountId));
        if(!account.getUser().getId().equals(user.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Account does not belong to the User");
        }
        account.setApproved(updateAccount.isApproved());
        accountRepository.save(account);

        return account;
    }

    @Override
    public Double viewBalance(Long userId, Long accountId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("users","userId",userId));
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(()-> new ResourceNotFoundException("accounts","accountId",accountId));
        if(!account.getUser().getId().equals(user.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Account does not belong to the User");
        }
        return account.getBalance();

    }

    @Override
    public void deposit(Long accountId, double amount,Account updateAccount) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(()-> new ResourceNotFoundException("accounts","accountId",accountId));
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);

    }

    @Override
    public void withdraw(Long accountId, double amount, Account updateAccount) {
        Account account = accountRepository
                .findById(accountId)
                .orElseThrow(()-> new ResourceNotFoundException("accounts","accountId",accountId));
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        account.setBalance(account.getBalance()-amount);

    }

    @Override
    public void moneyTransfer(Long accountId, Long fromAccountId, Long toAccountId, double amount, Account updateAccount) {
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("accounts","accountId",accountId));
        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("accounts","accountId",accountId));

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds in source account");
        }
        fromAccount.setBalance(fromAccount.getBalance()-amount);
        toAccount.setBalance(toAccount.getBalance()+amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);



    }
}
