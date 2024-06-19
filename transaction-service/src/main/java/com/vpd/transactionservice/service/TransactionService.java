package com.vpd.transactionservice.service;

import com.vpd.transactionservice.dto.APIResponse;
import com.vpd.transactionservice.dto.PaymentRequest;
import com.vpd.transactionservice.dto.TransferRequest;
import com.vpd.transactionservice.dto.WithdrawRequest;
import com.vpd.transactionservice.entity.Transaction;
import reactor.core.publisher.Mono;

public interface TransactionService {
    Mono<APIResponse<Transaction>> makePayment(PaymentRequest request);
    Mono<APIResponse<Transaction>> withdraw(WithdrawRequest request);
    Mono<APIResponse<Transaction>> transfer(TransferRequest request);
}
