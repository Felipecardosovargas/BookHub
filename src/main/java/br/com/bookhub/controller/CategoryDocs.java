package br.com.bookhub.controller;

import br.com.bookhub.controller.reponse.CategoryResponse;
import br.com.bookhub.controller.request.CategoryRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Category", description = "Resource for managing book categories including creation, listing, retrieval, " +
        "and deletion.")
public interface CategoryDocs {

    @Operation(
            summary = "Get all categories",
            description = "Retrieves a list of all book categories available in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of categories successfully retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error while fetching categories")
    })
    ResponseEntity<List<CategoryResponse>> getAllCategories();

    @Operation(
            summary = "Create a new category",
            description = "Adds a new category to the system. The request must contain the name and optionally a " +
                    "description.",
            security = @SecurityRequirement(name = "BearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category successfully created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body or validation errors"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required",
                    content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<CategoryResponse> saveCategory(
            @RequestBody(
                    description = "Category to be created",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CategoryRequest.class),
                            examples = @ExampleObject(name = "New Category", value = "{ \"name\": \"Science Fiction\", " +
                                    "\"description\": \"Books involving futuristic or technological themes.\" }")
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody CategoryRequest request);

    @Operation(
            summary = "Get category by ID",
            description = "Retrieves a specific category based on its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category successfully retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponse.class))),
            @ApiResponse(responseCode = "404", description = "Category not found with the given ID")
    })
    ResponseEntity<CategoryResponse> getCategoryById(
            @Parameter(description = "ID of the category to retrieve", required = true, example = "3")
            @PathVariable Long id);

    @Operation(
            summary = "Delete category by ID",
            description = "Deletes a category using its ID. If the category does not exist, nothing is deleted but a " +
                    "204 response is returned.",
            security = @SecurityRequirement(name = "BearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category successfully deleted or did not exist"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error during deletion")
    })
    ResponseEntity<Void> deleteCategoryById(
            @Parameter(description = "ID of the category to delete", required = true, example = "3")
            @PathVariable Long id);
}