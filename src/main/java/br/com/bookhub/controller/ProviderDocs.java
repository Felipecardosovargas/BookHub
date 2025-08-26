package br.com.bookhub.controller;

import br.com.bookhub.controller.reponse.ProviderResponse;
import br.com.bookhub.controller.request.ProviderRequest;
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

@Tag(name = "Provider", description = "Resource for managing book providers, including creation, listing, retrieval, " +
        "and deletion.")
public interface ProviderDocs {

    @Operation(
            summary = "Get all providers",
            description = "Retrieves a list of all providers available in the system. Each provider represents a " +
                    "company or entity responsible for distributing books."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of providers successfully retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProviderResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error while retrieving providers")
    })
    ResponseEntity<List<ProviderResponse>> getAllProviders();

    @Operation(
            summary = "Create a new provider",
            description = "Adds a new provider to the system. Providers are typically companies or services that make " +
                    "books available.",
            security = @SecurityRequirement(name = "BearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Provider successfully created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProviderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or constraint violation"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required",
                    content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<ProviderResponse> saveProvider(
            @RequestBody(
                    description = "Provider data to be created",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ProviderRequest.class),
                            examples = @ExampleObject(name = "New Provider", value = "{ \"name\": \"Amazon\", " +
                                    "\"website\": \"https://amazon.com\" }")
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody ProviderRequest request);

    @Operation(
            summary = "Get provider by ID",
            description = "Retrieves a specific provider based on its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Provider successfully retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProviderResponse.class))),
            @ApiResponse(responseCode = "404", description = "Provider not found with the given ID")
    })
    ResponseEntity<ProviderResponse> getProviderById(
            @Parameter(description = "ID of the provider to retrieve", required = true, example = "5")
            @PathVariable Long id);

    @Operation(
            summary = "Delete provider by ID",
            description = "Deletes a provider using its ID. Returns 204 even if the provider does not exist.",
            security = @SecurityRequirement(name = "BearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Provider successfully deleted or did not exist"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error during deletion")
    })
    ResponseEntity<Void> deleteProviderById(
            @Parameter(description = "ID of the provider to delete", required = true, example = "5")
            @PathVariable Long id);
}