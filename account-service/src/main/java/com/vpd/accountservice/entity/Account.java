package com.vpd.accountservice.entity;

import com.vpd.accountservice.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Account extends BaseEntity{
    private String name;
    @ManyToOne
    private AccountType accountType;
    private BigDecimal balance;
    private String description;
    private String accountNumber;
    private BigDecimal accountOpeningAmount;
    private Long customerId;
    private LocalDateTime dateOpened;
    private Long openedBy;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

}
