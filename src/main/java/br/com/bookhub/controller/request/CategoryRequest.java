package br.com.bookhub.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

@Schema(name = "CategoryRequest", description = "Request payload to create or update a book category.")
public record CategoryRequest(

        @NotEmpty(message = "Category name is mandatory")
        @Schema(description = "Name of the category", example = "Science Fiction")
        String name
) {}
