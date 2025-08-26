package br.com.bookhub.controller;

import br.com.bookhub.controller.reponse.LoginResponse;
import br.com.bookhub.controller.reponse.UserResponse;
import br.com.bookhub.controller.request.LoginRequest;
import br.com.bookhub.controller.request.UserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;

@Tag(name = "Authentication", description = "Endpoints for user registration and authentication using JWT tokens.")
public interface AuthDocs {

    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account in the system. Requires email, password, and optionally other " +
                    "user details."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully registered",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid user data or constraint violation",
                    content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<UserResponse> register(
            @RequestBody(
                    description = "User registration data",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UserRequest.class),
                            examples = @ExampleObject(name = "Register User", value = "{ \"name\": \"Jane Doe\", " +
                                    "\"email\": \"jane.doe@example.com\", \"password\": \"securePass123\" }")
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody UserRequest request);

    @Operation(
            summary = "Authenticate user and generate JWT token",
            description = "Authenticates a user using email and password. Returns a JWT token on success, which " +
                    "must be used in subsequent authorized requests."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully authenticated and token generated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials or authentication failed",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Malformed request body or missing credentials",
                    content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<LoginResponse> login(
            @RequestBody(
                    description = "Login credentials: email and password",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = LoginRequest.class),
                            examples = @ExampleObject(name = "Login User", value = "{ \"email\": " +
                                    "\"jane.doe@example.com\", \"password\": \"securePass123\" }")
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody LoginRequest request);
}