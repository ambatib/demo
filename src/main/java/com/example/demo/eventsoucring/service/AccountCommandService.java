package com.example.demo.eventsoucring.service;

import java.util.concurrent.CompletableFuture;

import com.example.demo.eventsourcing.dto.AccountCreateDTO;
import com.example.demo.eventsourcing.dto.MoneyCreditDTO;
import com.example.demo.eventsourcing.dto.MoneyDebitDTO;

public interface AccountCommandService {

    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO);
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO);
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO);
}