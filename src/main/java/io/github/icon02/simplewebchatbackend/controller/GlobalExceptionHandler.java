package io.github.icon02.simplewebchatbackend.controller;

import io.github.icon02.simplewebchatbackend.exception.FileNotSupportedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FileNotSupportedException.class)
    public ResponseEntity<?> handleFileNotSupportedException(FileNotSupportedException exception) {
        return ResponseEntity.badRequest().body(CustomErrorCodes.FILE_NOT_SUPPORTED);
    }
}
