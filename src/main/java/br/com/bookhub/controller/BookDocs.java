package br.com.bookhub.controller;

import br.com.bookhub.controller.reponse.BookResponse;
import br.com.bookhub.controller.request.BookRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book", description = "Resource for managing book-related operations including creation, retrieval, updating, " +
        "and deletion of books.")
public interface BookDocs {

    @Operation(
            summary = "Create a new book",
            description = "Creates and persists a new book entity in the system. The book details must be provided in the " +
                    "request body as JSON.",
            security = @SecurityRequirement(name = "BearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book successfully created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body or validation errors",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error during book creation",
                    content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<BookResponse> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Book object to be created",
                    required = true,
                    content = @Content(schema = @Schema(implementation = BookRequest.class),
                            examples = @ExampleObject(name = "New Book", value = "{ \"title\": \"Clean Code\", \"author\": " +
                                    "\"Robert C. Martin\", \"isbn\": \"9780132350884\", \"categoryId\": 1 }"))
            )
            @RequestBody BookRequest request);

    @Operation(
            summary = "Retrieve all books",
            description = "Fetches a list of all books currently stored in the system. The response is an array of book " +
                    "representations."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of books successfully retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error during retrieval",
                    content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<List<BookResponse>> findAll();

    @Operation(
            summary = "Retrieve a book by ID",
            description = "Fetches a specific book using its unique identifier. If the book is not found, a 404 status " +
                    "is returned."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book successfully retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookResponse.class))),
            @ApiResponse(responseCode = "404", description = "Book not found for the given ID",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<BookResponse> findById(
            @Parameter(description = "ID of the book to be retrieved", required = true, example = "1")
            @PathVariable Long id);

    @Operation(
            summary = "Update a book by ID",
            description = "Updates an existing book using the provided ID and request body. If the book does not exist, " +
                    "returns 404.",
            security = @SecurityRequirement(name = "BearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book successfully updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Book not found for the given ID",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<BookResponse> update(
            @Parameter(description = "ID of the book to be updated", required = true, example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated book data",
                    required = true,
                    content = @Content(schema = @Schema(implementation = BookRequest.class),
                            examples = @ExampleObject(name = "Updated Book", value = "{ \"title\": \"Clean Architecture\"," +
                                    " \"author\": \"Robert C. Martin\", \"isbn\": \"9780134494166\", \"categoryId\": 2 }"))
            )
            @RequestBody BookRequest request);

    @Operation(
            summary = "Retrieve books by category",
            description = "Returns a list of books filtered by their category ID. Useful for category-based search or " +
                    "filtering."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books successfully retrieved for the given category",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid category ID supplied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<List<BookResponse>> findByCategory(
            @Parameter(description = "ID of the category used for filtering books", required = true, example = "2")
            @RequestParam Long category);

    @Operation(
            summary = "Delete a book by ID",
            description = "Deletes a book identified by the given ID. If the book is not found, returns 404. If " +
                    "deletion is successful, returns 204 No Content.",
            security = @SecurityRequirement(name = "BearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Book successfully deleted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Book not found for the given ID",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error during deletion",
                    content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<Void> delete(
            @Parameter(description = "ID of the book to be deleted", required = true, example = "1")
            @PathVariable Long id);
}