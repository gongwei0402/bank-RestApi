package com.cogent.bank_rest_api.service;

import com.cogent.bank_rest_api.Entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Long accountId, Transaction newTransaction);
    List<Transaction> getTransactionByAccountId(Long accountId);


}
