package com.vpd.accountservice.repository;

import com.vpd.accountservice.entity.Account;
import com.vpd.accountservice.entity.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByAccountType(AccountType accountType);

    Optional<Account> findAccountByAccountNumber(String accountNumber);

    void deleteAccountByAccountNumber(String accountNumber);
}
