package br.com.bookhub.controller;

import br.com.bookhub.config.TokenService;
import br.com.bookhub.controller.reponse.LoginResponse;
import br.com.bookhub.controller.reponse.UserResponse;
import br.com.bookhub.controller.request.LoginRequest;
import br.com.bookhub.controller.request.UserRequest;
import br.com.bookhub.entity.User;
import br.com.bookhub.exception.UsernameOrPasswordInvalidException;
import br.com.bookhub.mapper.UserMapper;
import br.com.bookhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bookhub/auth")
@RequiredArgsConstructor
public class AuthController implements AuthDocs {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest request) {
        User savedUser = userService.save(UserMapper.toUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(savedUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken userPass =
                    new UsernamePasswordAuthenticationToken(request.email(), request.password());
            Authentication authentication = authenticationManager.authenticate(userPass);
            User user = (User) authentication.getPrincipal();
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (BadCredentialsException e) {
            throw new UsernameOrPasswordInvalidException(e.getMessage());
        }
    }
}