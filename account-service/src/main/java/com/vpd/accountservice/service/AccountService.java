package com.vpd.accountservice.service;

import com.vpd.accountservice.dto.APIResponse;
import com.vpd.accountservice.dto.CreateAccountRequest;
import com.vpd.accountservice.entity.Account;

import java.util.List;

public interface AccountService {
    APIResponse<Account> createAccount(CreateAccountRequest request);

    APIResponse<Account> getAccount(Long id);

    APIResponse<Account> getAccountByAccountNumber(String accountNumber);

    APIResponse<Account> updateAccount(Long id, CreateAccountRequest request);

    APIResponse<Account> updateAccountByAccountNumber(String accountNumber, CreateAccountRequest request);

    APIResponse<Account> deleteAccount(Long id);


    APIResponse<Account> deleteAccountByAccountNumber(String accountNumber);

    APIResponse<List<Account>> getAllAccounts();
}
