package com.vpd.authorizationserver.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIError> handleApiException(APIException ex, HttpServletRequest request){
       APIError apiError = APIError.builder()
               .error(ex.getLocalizedMessage())
               .message(ex.getMessage())
               .statusCode(ex.getStatusCode())
               .path(request.getRequestURI())
               .build();
       return ResponseEntity.status(ex.getStatusCode()).body(apiError);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request){
        String errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        APIError apiError = APIError.builder()
                .error(errorMessages)
                .message("Validation Failed")
                .statusCode(400)
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(ex.getStatusCode()).body(apiError);
    }
}
