package br.com.bookhub.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
@Schema(name = "ProviderRequest", description = "Request payload to create or update a book provider service.")
public record ProviderRequest(

        @NotEmpty(message = "Provider service name is mandatory")
        @Schema(description = "Name of the provider service", example = "Amazon")
        String name
) {}
