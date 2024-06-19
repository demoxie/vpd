package com.vpd.transactionservice.repository;

import com.vpd.transactionservice.entity.Transaction;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends R2dbcRepository<Transaction, Long> {
    Optional<Transaction> findTransactionByCustomerIdAndAccountId(Long customerId, Long accountId);
}
