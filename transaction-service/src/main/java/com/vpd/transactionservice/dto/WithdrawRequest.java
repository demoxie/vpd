package com.vpd.transactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawRequest {
    private String accountPin;
    private BigDecimal amount;
    private Long customerId;
    private String accountNumber;
}
