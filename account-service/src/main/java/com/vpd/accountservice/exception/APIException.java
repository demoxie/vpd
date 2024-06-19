package com.vpd.accountservice.exception;

import lombok.*;
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
