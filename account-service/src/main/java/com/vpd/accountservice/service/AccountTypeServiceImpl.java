package com.vpd.accountservice.service;

import com.vpd.accountservice.dto.APIResponse;
import com.vpd.accountservice.dto.CreateAccountRequest;
import com.vpd.accountservice.dto.CreateAccountTypeRequest;
import com.vpd.accountservice.entity.Account;
import com.vpd.accountservice.entity.AccountType;
import com.vpd.accountservice.enums.AccountTypeStatus;
import com.vpd.accountservice.exception.APIException;
import com.vpd.accountservice.repository.AccountRepository;
import com.vpd.accountservice.repository.AccountTypeRepository;
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
public class AccountTypeServiceImpl implements AccountTypeService{
    private final AccountTypeRepository accountTypeRepository;
    private final ModelMapper mapper;
    @Override
    public APIResponse<AccountType> createAccountType(CreateAccountTypeRequest request) {
        Optional<AccountType> accountOptional = accountTypeRepository.findAccountTypeByName(request.getName());
        if(accountOptional.isPresent()) {
            throw APIException.builder()
                    .status(409)
                   .message("Account type already exists")
                    .timestamp(LocalDateTime.now())
                    .build();
        }
        AccountType account = mapper.map(request, AccountType.class);
        account.setStatus(AccountTypeStatus.ACTIVE);
        AccountType savedAccount = accountTypeRepository.save(account);
        return APIResponse.<AccountType>builder()
                .status(200)
               .message("Account type created successfully")
                .data(savedAccount)
               .build();
    }

    @Override
    public APIResponse<AccountType> getAccountType(Long id) {
        return APIResponse.<AccountType>builder()
                .status(200)
               .message("Account type retrieved successfully")
                .data(accountTypeRepository.findById(id).orElse(null))
               .build();
    }

    @Override
    public APIResponse<AccountType> getAccountTypeByName(String name) {
        return APIResponse.<AccountType>builder()
                .status(200)
                .message("Account type retrieved successfully")
                .data(accountTypeRepository.findAccountTypeByName(name).orElse(null))
                .build();
    }

    @Override
    public APIResponse<AccountType> updateAccountType(Long id, CreateAccountTypeRequest request) {
        Optional<AccountType> accountOptional = accountTypeRepository.findById(id);
        if (accountOptional.isEmpty()) {
            throw APIException.builder()
                   .status(404)
                   .message("Account type not found")
                   .timestamp(LocalDateTime.now())
                   .build();
        }

        AccountType account = mapper.map(request, AccountType.class);
        account.setStatus(request.getStatus());
        account.setName(request.getName());
        account.setDescription(request.getDescription());
        AccountType updatedAccount = accountTypeRepository.save(account);
        return APIResponse.<AccountType>builder()
                .status(200)
               .message("Account type updated successfully")
                .data(updatedAccount)
               .build();
    }

    @Override
    public APIResponse<AccountType> deleteAccountType(Long id) {
        accountTypeRepository.deleteById(id);
        return APIResponse.<AccountType>builder()
                .status(200)
               .message("Account type deleted successfully")
                .data(null)
               .build();
    }

    @Override
    public APIResponse<List<AccountType>> getAllAccountTypes() {
        return APIResponse.<List<AccountType>>builder()
                .status(200)
                .message("Account types fetched successfully")
                .data(accountTypeRepository.findAll())
                .build();
    }
}
