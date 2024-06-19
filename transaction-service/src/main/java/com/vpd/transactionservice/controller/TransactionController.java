package com.vpd.transactionservice.controller;

import com.vpd.transactionservice.dto.APIResponse;
import com.vpd.transactionservice.dto.PaymentRequest;
import com.vpd.transactionservice.dto.WithdrawRequest;
import com.vpd.transactionservice.entity.Transaction;
import com.vpd.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/make-payment")
    public Mono<ResponseEntity<APIResponse<Transaction>>> makePayment(@RequestBody PaymentRequest request, ServerWebExchange exchange) {
        var headers = exchange.getRequest().getHeaders().get("X-auth-user-id");
        boolean customerIdExists = headers != null && !headers.isEmpty();
        if (customerIdExists) {
            String customerId = headers.get(0);
            request.setUserId(Long.valueOf(customerId));
            Mono<APIResponse<Transaction>> apiResponseMono = transactionService.makePayment(request);
            return apiResponseMono
                    .map(apiResponse -> ResponseEntity.status(apiResponse.getStatus()).body(apiResponse));
        }
        return Mono.just(ResponseEntity.badRequest().build());
    }

    @PostMapping("/withdraw")
    public Mono<ResponseEntity<APIResponse<Transaction>>> debit(@RequestBody WithdrawRequest request, ServerWebExchange exchange) {
        var headers = exchange.getRequest().getHeaders().get("X-auth-user-id");
        boolean customerIdExists = headers!= null &&!headers.isEmpty();
        if (customerIdExists) {
            String customerId = headers.get(0);
            request.setCustomerId(Long.valueOf(customerId));
            Mono<APIResponse<Transaction>> apiResponseMono = transactionService.withdraw(request);
            return apiResponseMono
                   .map(apiResponse -> ResponseEntity.status(apiResponse.getStatus()).body(apiResponse));
        }
        return Mono.just(ResponseEntity.badRequest().build());
    }

}
