package br.com.bookhub.controller.reponse;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LoginResponse", description = "JWT token returned after successful authentication.")
public record LoginResponse(

        @Schema(
                description = "JWT token that must be included in the Authorization header for secured requests. Format: Bearer <token>",
                example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
        )
        String token
) {}
