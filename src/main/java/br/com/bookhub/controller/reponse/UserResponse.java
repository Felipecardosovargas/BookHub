package br.com.bookhub.controller.reponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "UserResponse", description = "Represents the data returned after user registration or for authenticated users.")
public record UserResponse(

        @Schema(description = "Unique identifier of the user", example = "42")
        Long id,

        @Schema(description = "Username chosen by the user", example = "john_doe")
        String username,

        @Schema(description = "Email address of the user", example = "john.doe@example.com")
        String email
) {}
