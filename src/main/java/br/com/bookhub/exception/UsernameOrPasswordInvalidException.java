package br.com.bookhub.exception;

import org.springframework.http.HttpStatus;

public class UsernameOrPasswordInvalidException extends RuntimeException {
    public UsernameOrPasswordInvalidException(String message) {
        super(message);
    }
}
