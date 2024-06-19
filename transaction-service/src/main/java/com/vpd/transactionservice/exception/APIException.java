package com.vpd.transactionservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpMethod;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Builder
public class APIException extends RuntimeException{
    private final String message;
    private final Integer status;
    private final String path;
    private final LocalDateTime timestamp;
    private final HttpMethod method;
}
