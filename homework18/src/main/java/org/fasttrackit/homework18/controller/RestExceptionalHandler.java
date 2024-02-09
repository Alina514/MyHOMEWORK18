package org.fasttrackit.homework18.controller;

import lombok.Builder;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class RestExceptionalHandler {
        @ExceptionHandler(ResourceNotFoundException.class)
        @ResponseStatus(BAD_REQUEST)
        public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException exception) {
            return ErrorResponse.builder()
                    .message(exception.getMessage())
                    .build();
        }

        @Builder
        record ErrorResponse(String message) {

        }
}
