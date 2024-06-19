package com.vpd.accountservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vpd.accountservice.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAccountRequest {
    private String name;
    private Long accountTypeId;
    private BigDecimal accountOpeningAmount;
    private Long customerId;
    private String description;
    private Long openedBy;
    private AccountStatus status;
    private String accountNumber;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm'Z'",
            shape = JsonFormat.Shape.STRING,
            timezone = "UTC"
    )
    private LocalDateTime dateOpened;
    private BigDecimal balance;

}
