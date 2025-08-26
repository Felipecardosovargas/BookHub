package br.com.bookhub.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
@Schema(name = "UserRequest", description = "Request payload for user registration or update.")
public record UserRequest(

        @NotEmpty(message = "Username is mandatory")
        @Schema(description = "Username chosen by the user", example = "john_doe")
        String username,

        @NotEmpty(message = "Email is mandatory")
        @Email(message = "Email must be valid")
        @Schema(description = "User's email address", example = "john.doe@example.com")
        String email,

        @NotEmpty(message = "Password is mandatory")
        @Schema(description = "User's password", example = "StrongPassw0rd!")
        String password
) {}
