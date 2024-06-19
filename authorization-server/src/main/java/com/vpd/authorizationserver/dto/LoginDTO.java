package com.vpd.authorizationserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginDTO {
    @NotEmpty(message = "Email name is required")
    @NotNull(message = "Email name cannot be null")
    private String email;
    @NotEmpty(message = "Password is required")
    @NotNull(message = "Password cannot be null")
    private String password;
}
