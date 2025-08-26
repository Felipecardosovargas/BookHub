package br.com.bookhub.controller.reponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "ProviderResponse", description = "Represents a provider/distributor of books.")
public record ProviderResponse(

        @Schema(description = "Unique identifier of the provider", example = "7")
        Long id,

        @Schema(description = "Name of the provider or distributor", example = "Amazon")
        String name
) {}
