package com.cogent.bank_rest_api.controller;

import com.cogent.bank_rest_api.Entity.Transaction;
import com.cogent.bank_rest_api.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/transactions")
@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/{accountId}/transactions")
    public ResponseEntity<Transaction> createTransaction(@PathVariable("accountId") Long accountId,
                                                     @Valid @RequestBody Transaction newTransaction) {
        Transaction transaction = transactionService.createTransaction(accountId, newTransaction);
        return ResponseEntity.ok(transaction);
    }
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionByAccountId(@PathVariable("accountId") Long accountId) {
        List<Transaction> transactions = transactionService.getTransactionByAccountId(accountId);
        return ResponseEntity.ok(transactions);
    }

}
