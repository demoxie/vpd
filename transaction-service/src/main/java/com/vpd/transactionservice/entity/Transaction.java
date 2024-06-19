package com.vpd.transactionservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction extends BaseEntity{
    private String description;
    private BigDecimal amount;
    private String currency;
    private String status;
    private String type;
    private String category;
    private String merchant;
    private String reference;
    private Long customerId;
    private Long accountId;
    private String product;
}
