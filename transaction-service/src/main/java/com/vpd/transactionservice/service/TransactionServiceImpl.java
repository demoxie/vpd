package com.vpd.transactionservice.service;

import com.vpd.transactionservice.config.client.ClientSelector;
import com.vpd.transactionservice.dto.APIResponse;
import com.vpd.transactionservice.dto.PaymentRequest;
import com.vpd.transactionservice.dto.TransferRequest;
import com.vpd.transactionservice.dto.WithdrawRequest;
import com.vpd.transactionservice.entity.Transaction;
import com.vpd.transactionservice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final ClientSelector clientSelector;
    @Override
    public Mono<APIResponse<Transaction>> makePayment(PaymentRequest request) {
        return null;
    }

    @Override
    public Mono<APIResponse<Transaction>> withdraw(WithdrawRequest request) {
        return null;
    }

    @Override
    public Mono<APIResponse<Transaction>> transfer(TransferRequest request) {
        return null;
    }
}
