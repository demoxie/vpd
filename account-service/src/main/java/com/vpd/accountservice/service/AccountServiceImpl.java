package com.vpd.accountservice.service;

import com.vpd.accountservice.dto.APIResponse;
import com.vpd.accountservice.dto.CreateAccountRequest;
import com.vpd.accountservice.entity.Account;
import com.vpd.accountservice.exception.APIException;
import com.vpd.accountservice.repository.AccountRepository;
import com.vpd.accountservice.repository.AccountTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final ModelMapper mapper;
    @Override
    public APIResponse<Account> createAccount(CreateAccountRequest request) {
        Optional<Account> accountOptional = accountRepository.findAccountByAccountType(accountTypeRepository.findById(request.getAccountTypeId()).orElse(null));
        if(accountOptional.isPresent()) {
            throw APIException.builder()
                    .status(409)
                   .message("Account already exists")
                    .timestamp(LocalDateTime.now())
                    .build();
        }
        Account account = mapper.map(request, Account.class);
        Account savedAccount = accountRepository.save(account);
        return APIResponse.<Account>builder()
                .status(200)
               .message("Account created successfully")
                .data(savedAccount)
               .build();
    }

    @Override
    public APIResponse<Account> getAccount(Long id) {
        return APIResponse.<Account>builder()
                .status(200)
               .message("Account retrieved successfully")
                .data(accountRepository.findById(id).orElse(null))
               .build();
    }

    @Override
    public APIResponse<Account> getAccountByAccountNumber(String accountNumber) {
        return APIResponse.<Account>builder()
                .status(200)
                .message("Account retrieved successfully")
                .data(accountRepository.findAccountByAccountNumber(accountNumber).orElse(null))
                .build();
    }

    @Override
    public APIResponse<Account> updateAccount(Long id, CreateAccountRequest request) {
        Optional<Account> accountOptional = accountRepository.findAccountByAccountNumber(request.getAccountNumber());
        if (accountOptional.isEmpty()) {
            throw APIException.builder()
                   .status(404)
                   .message("Account not found")
                   .timestamp(LocalDateTime.now())
                   .build();
        }

        Account account = mapper.map(request, Account.class);
        account.setId(id);
        account.setAccountType(accountTypeRepository.findById(request.getAccountTypeId()).orElse(null));
        account.setDateOpened(LocalDateTime.now());
        account.setOpenedBy(request.getOpenedBy());
        account.setAccountOpeningAmount(request.getAccountOpeningAmount());
        account.setCustomerId(request.getCustomerId());
        account.setStatus(request.getStatus());
        Account updatedAccount = accountRepository.save(account);
        return APIResponse.<Account>builder()
                .status(200)
               .message("Account updated successfully")
                .data(updatedAccount)
               .build();
    }

    @Override
    public APIResponse<Account> updateAccountByAccountNumber(String accountNumber, CreateAccountRequest request) {
        Optional<Account> accountOptional = accountRepository.findAccountByAccountNumber(request.getAccountNumber());
        if (accountOptional.isEmpty()) {
            throw APIException.builder()
                    .status(404)
                    .message("Account not found")
                    .timestamp(LocalDateTime.now())
                    .build();
        }

        Account account = mapper.map(request, Account.class);
        account.setAccountType(accountTypeRepository.findById(request.getAccountTypeId()).orElse(null));
        account.setDateOpened(LocalDateTime.now());
        account.setOpenedBy(request.getOpenedBy());
        account.setAccountOpeningAmount(request.getAccountOpeningAmount());
        account.setCustomerId(request.getCustomerId());
        account.setStatus(request.getStatus());
        Account updatedAccount = accountRepository.save(account);
        return APIResponse.<Account>builder()
                .status(200)
                .message("Account updated successfully")
                .data(updatedAccount)
                .build();
    }

    @Override
    public APIResponse<Account> deleteAccount(Long id) {
        accountRepository.deleteById(id);
        return APIResponse.<Account>builder()
                .status(200)
               .message("Account deleted successfully")
                .data(null)
               .build();
    }

    @Override
    public APIResponse<Account> deleteAccountByAccountNumber(String accountNumber) {
        accountRepository.deleteAccountByAccountNumber(accountNumber);
        return APIResponse.<Account>builder()
                .status(200)
                .message("Account deleted successfully")
                .data(null)
                .build();
    }

    @Override
    public APIResponse<List<Account>> getAllAccounts() {
        return APIResponse.<List<Account>>builder()
                .status(200)
                .message("Accounts fetched successfully")
                .data(accountRepository.findAll())
                .build();
    }
}
