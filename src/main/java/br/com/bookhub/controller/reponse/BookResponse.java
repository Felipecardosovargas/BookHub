package br.com.bookhub.controller.reponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
@Schema(name = "BookResponse", description = "Represents the detailed response of a book, including categories and providers.")
public record BookResponse(

        @Schema(description = "Unique identifier of the book", example = "101")
        Long id,

        @Schema(description = "Title of the book", example = "The Pragmatic Programmer")
        String title,

        @Schema(description = "Brief description or synopsis of the book", example = "A guide for software developers to improve their craft.")
        String description,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        @Schema(description = "Date the book was published", example = "01/11/1999", type = "string", format = "date")
        LocalDate publishDate,

        @Schema(description = "Average rating of the book from 0.0 to 5.0", example = "4.8")
        double rating,

        @Schema(description = "List of categories the book belongs to")
        List<CategoryResponse> categories,

        @Schema(description = "List of providers/distributors offering the book")
        List<ProviderResponse> providers
) {}
