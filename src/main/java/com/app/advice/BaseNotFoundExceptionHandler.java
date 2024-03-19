package com.app.advice;

import com.app.exception.BaseNotFoundException;
import com.app.service.MessageRenderer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class BaseNotFoundExceptionHandler {

    private final MessageRenderer messageRenderer;

    @ExceptionHandler(BaseNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(BaseNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(messageRenderer.render(ex.getMessageCode(), ex.getArgs())));
    }

    @Getter
    @RequiredArgsConstructor
    public static class ErrorResponse {

        private final String message;
    }
}
