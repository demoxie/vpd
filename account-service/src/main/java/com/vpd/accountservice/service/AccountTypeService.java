package com.vpd.accountservice.service;



import com.vpd.accountservice.dto.APIResponse;
import com.vpd.accountservice.dto.CreateAccountTypeRequest;
import com.vpd.accountservice.entity.AccountType;

import java.util.List;

public interface AccountTypeService {
    APIResponse<AccountType> createAccountType(CreateAccountTypeRequest request);

    APIResponse<AccountType> getAccountType(Long id);

    APIResponse<AccountType> getAccountTypeByName(String name);

    APIResponse<AccountType> updateAccountType(Long id, CreateAccountTypeRequest request);

    APIResponse<AccountType> deleteAccountType(Long id);
    APIResponse<List<AccountType>> getAllAccountTypes();
}
