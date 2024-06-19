package com.vpd.accountservice.dto;

import com.vpd.accountservice.enums.AccountTypeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAccountTypeRequest {
    private String name;
    private String description;
    private AccountTypeStatus status;
}
