package br.com.bookhub.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Schema(name = "LoginRequest", description = "Request payload for user login authentication.")
public record LoginRequest(

        @NotEmpty(message = "Email is mandatory")
        @Email(message = "Email must be valid")
        @Schema(description = "User email address", example = "user@example.com")
        String email,

        @NotEmpty(message = "Password is mandatory")
        @Schema(description = "User password", example = "StrongPassword123!")
        String password
) {}
