package com.cogent.bank_rest_api.controller;

import com.cogent.bank_rest_api.Entity.Account;
import com.cogent.bank_rest_api.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Account> applyForAccount(@PathVariable("userId") Long userId,
                                                   @Valid @RequestBody Account newAccount){
        Account account = accountService.applyForAccount(userId, newAccount);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{userId}/accounts/{accountId}")
    public ResponseEntity<Account> approveAccount(@PathVariable("userId") Long userId,
                                                   @PathVariable("accountId") Long accountId,
                                                   @Valid @RequestBody Account updateAccount){
        Account account = accountService.approveAccount(userId,accountId, updateAccount);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<Double> viewBalance(@PathVariable("userId") Long userId,
                                              @PathVariable("accountId") Long accountId){
        Double account = accountService.viewBalance(userId, accountId);
        return ResponseEntity.ok(account);
    }


}
