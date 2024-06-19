package com.vpd.gateway.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(APIException.class)
    public Mono<ResponseEntity<APIError>> handleApiException(APIException ex, ServerWebExchange request){
       APIError apiError = APIError.builder()
               .error(ex.getLocalizedMessage())
               .message(ex.getMessage())
               .statusCode(ex.getStatusCode())
               .path(request.getRequest().getPath().toString())
               .build();
       return Mono.just(ResponseEntity.status(ex.getStatusCode()).body(apiError));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Mono<ResponseEntity<APIError>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, ServerWebExchange request){
        String errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        APIError apiError = APIError.builder()
                .error(errorMessages)
                .message("Validation Failed")
                .statusCode(400)
                .path(request.getRequest().getPath().toString())
                .build();
        return Mono.just(ResponseEntity.status(ex.getStatusCode()).body(apiError));
    }
}
