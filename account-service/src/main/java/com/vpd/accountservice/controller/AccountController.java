package com.vpd.accountservice.controller;

import com.vpd.accountservice.dto.APIResponse;
import com.vpd.accountservice.dto.CreateAccountRequest;
import com.vpd.accountservice.entity.Account;
import com.vpd.accountservice.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final AccountService accountService;
    @PostMapping("/create")
    public ResponseEntity<APIResponse<Account>> createAccount(@Valid @RequestBody CreateAccountRequest request, HttpServletRequest httpServletRequest) {
        var headers = httpServletRequest.getHeaders("X-auth-user-id");
        boolean customerIdExists = headers.hasMoreElements();
        if (customerIdExists) {
            String customerId = headers.nextElement().trim();
            request.setCustomerId(Long.valueOf(customerId));
            APIResponse<Account> apiResponse = accountService.createAccount(request);
            return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
        }
        return ResponseEntity.status(400).body(APIResponse.<Account>builder()
               .status(400)
               .message("Customer ID is invalid or not provided")
               .data(null)
               .build());

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse<Account>> updateAccount(@Valid @RequestBody CreateAccountRequest request, @PathVariable Long id) {
        APIResponse<Account> apiResponse = accountService.updateAccount(id, request);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<APIResponse<Account>> deleteAccount(@PathVariable Long id) {
        APIResponse<Account> apiResponse = accountService.deleteAccount(id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<APIResponse<Account>> getAccount(@PathVariable Long id) {
        APIResponse<Account> apiResponse = accountService.getAccount(id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Account>>> getAccounts() {
        APIResponse<List<Account>> apiResponse = accountService.getAllAccounts();
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @GetMapping("/getByAccountNumber/{accountNumber}")
    public ResponseEntity<APIResponse<Account>> getAccountByAccountNumber(@PathVariable String accountNumber) {
        APIResponse<Account> apiResponse = accountService.getAccountByAccountNumber(accountNumber);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PutMapping("/updateByAccountNumber/{accountNumber}")
    public ResponseEntity<APIResponse<Account>> updateAccountByAccountNumber(@PathVariable String accountNumber, @Valid @RequestBody CreateAccountRequest request) {
        APIResponse<Account> apiResponse = accountService.updateAccountByAccountNumber(accountNumber, request);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @DeleteMapping("/deleteByAccountNumber/{accountNumber}")
    public ResponseEntity<APIResponse<Account>> deleteAccountByAccountNumber(@PathVariable String accountNumber) {
        APIResponse<Account> apiResponse = accountService.deleteAccountByAccountNumber(accountNumber);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
