package br.com.bookhub.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Schema(name = "BookRequest", description = "Request payload to create or update a book entity.")
public record BookRequest(

        @NotEmpty(message = "The book title is mandatory")
        @Schema(description = "Title of the book", example = "Clean Code")
        String title,

        @Schema(description = "Detailed description or synopsis of the book", example = "A handbook of agile software craftsmanship.")
        String description,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        @Schema(description = "Publication date of the book in dd/MM/yyyy format", example = "15/08/2008", type = "string", format = "date")
        LocalDate publishDate,

        @Schema(description = "Rating of the book, scale from 0.0 to 5.0", example = "4.7")
        double rating,

        @Schema(description = "List of category IDs to which this book belongs", example = "[1, 2, 3]")
        List<Long> categories,

        @Schema(description = "List of provider IDs that supply this book", example = "[5, 7]")
        List<Long> providers
) {}
