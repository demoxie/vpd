package com.vpd.gateway.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class APIException extends RuntimeException{
    private final String message;
    private final Integer statusCode;
}
