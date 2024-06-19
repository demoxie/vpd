package com.vpd.authorizationserver.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APIError {
    private String message;
    private String error;
    private Integer statusCode;
    private String path;
}
