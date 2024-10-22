package com.cogent.bank_rest_api.service.impl;

import com.cogent.bank_rest_api.Entity.Account;
import com.cogent.bank_rest_api.Entity.Transaction;
import com.cogent.bank_rest_api.exception.ResourceNotFoundException;
import com.cogent.bank_rest_api.repository.AccountRepository;
import com.cogent.bank_rest_api.repository.TransactionRepository;
import com.cogent.bank_rest_api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public Transaction createTransaction(Long accountId, Transaction newTransaction) {
       Account account = accountRepository
               .findById(accountId)
               .orElseThrow(()->new ResourceNotFoundException("accounts", "accountId",accountId));
       newTransaction.setAccount(account);
       return transactionRepository.save(newTransaction);
    }

    @Override
    public List<Transaction> getTransactionByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
