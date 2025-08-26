package br.com.bookhub.controller.reponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "CategoryResponse", description = "Represents a category assigned to books.")
public record CategoryResponse(

        @Schema(description = "Unique identifier of the category", example = "10")
        Long id,

        @Schema(description = "Name of the category", example = "Science Fiction")
        String name
) {}
