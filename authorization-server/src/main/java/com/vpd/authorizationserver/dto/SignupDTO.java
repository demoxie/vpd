package com.vpd.authorizationserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SignupDTO {
    @NotEmpty(message = "Name is required")
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotEmpty(message = "Email name is required")
    @NotNull(message = "Email name cannot be null")
    private String email;
    @NotEmpty(message = "Password name is required")
    @NotNull(message = "Password cannot be null")
    private String password;

}
