package com.vpd.accountservice.controller;

import com.vpd.accountservice.dto.APIResponse;
import com.vpd.accountservice.dto.CreateAccountRequest;
import com.vpd.accountservice.dto.CreateAccountTypeRequest;
import com.vpd.accountservice.entity.Account;
import com.vpd.accountservice.entity.AccountType;
import com.vpd.accountservice.service.AccountTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account-types")
@Slf4j
public class AccountTypeController {
    private final AccountTypeService accountTypeService;
    @PostMapping("/create")
    public ResponseEntity<APIResponse<AccountType>> createAccountType(@Valid @RequestBody CreateAccountTypeRequest request) {
        APIResponse<AccountType> apiResponse = accountTypeService.createAccountType(request);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<APIResponse<AccountType>> updateAccountType(@Valid @RequestBody CreateAccountTypeRequest request, @PathVariable Long id) {
        APIResponse<AccountType> apiResponse = accountTypeService.updateAccountType(id, request);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<APIResponse<AccountType>> deleteAccountType(@PathVariable Long id) {
        APIResponse<AccountType> apiResponse = accountTypeService.deleteAccountType(id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PostMapping("/get/{id}")
    public ResponseEntity<APIResponse<AccountType>> getAccount(@PathVariable Long id) {
        APIResponse<AccountType> apiResponse = accountTypeService.getAccountType(id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PostMapping("/names/{name}")
    public ResponseEntity<APIResponse<AccountType>> getAccountTypeByNAME(@PathVariable String name) {
        APIResponse<AccountType> apiResponse = accountTypeService.getAccountTypeByName(name);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
    @PostMapping("/all")
    public ResponseEntity<APIResponse<List<AccountType>>> getAllAccountTypes() {
        APIResponse<List<AccountType>> apiResponse = accountTypeService.getAllAccountTypes();
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
