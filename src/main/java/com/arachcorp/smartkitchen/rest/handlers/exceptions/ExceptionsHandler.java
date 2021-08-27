package com.arachcorp.smartkitchen.rest.handlers.exceptions;

import com.arachcorp.smartkitchen.services.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFoundException(ResourceNotFoundException e, HttpServletRequest req){
        log.error(e.getMessage());
        final HttpStatus status = HttpStatus.NOT_FOUND;
        final StandardError error = StandardError.builder()
                .code(status.value())
                .message("Resource Not Found Exception")
                .errors(Arrays.asList(e.getMessage()))
                .path(req.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(UpdateResourceException.class)
    public ResponseEntity<StandardError> updateResourceException(UpdateResourceException e, HttpServletRequest req){
        log.error(e.getMessage());
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        final StandardError error = StandardError.builder()
                .code(status.value())
                .message("Update Resource Exception")
                .errors(Arrays.asList(e.getMessage()))
                .path(req.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DeleteResourceException.class)
    public ResponseEntity<StandardError> deleteResourceException(DeleteResourceException e, HttpServletRequest req){
        log.error(e.getMessage());
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        final StandardError error = StandardError.builder()
                .code(status.value())
                .message("Delete Resource Exception")
                .errors(Arrays.asList(e.getMessage()))
                .path(req.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(CreateResourceException.class)
    public ResponseEntity<StandardError> createResourceException(CreateResourceException e, HttpServletRequest req){
        log.error(e.getMessage());
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        final StandardError error = StandardError.builder()
                .code(status.value())
                .message("Create Resource Exception")
                .errors(Arrays.asList(e.getMessage()))
                .path(req.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodNotValidException(MethodArgumentNotValidException e, HttpServletRequest req){
        log.error(e.getMessage());
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        final StandardError error = StandardError.builder()
                .code(status.value())
                .timestamp(LocalDateTime.now())
                .path(req.getRequestURI())
                .message("Payload Validation Exception")
                .errors(
                        e.getBindingResult().getAllErrors()
                                .stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .collect(Collectors.toList())
                ).build();
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<StandardError> invalidPasswordException(InvalidPasswordException e, HttpServletRequest req){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        final StandardError error = StandardError.builder()
                .code(status.value())
                .timestamp(LocalDateTime.now())
                .path(req.getRequestURI())
                .message("Invalid Password")
                .errors(
                        Collections.singletonList(e.getMessage())
                )
                .build();
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<StandardError> usernameNotFoundException(UsernameNotFoundException e, HttpServletRequest req){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        final StandardError error = StandardError.builder()
                .code(status.value())
                .timestamp(LocalDateTime.now())
                .path(req.getRequestURI())
                .message("User Not Found")
                .errors(
                        Arrays.asList(e.getMessage())
                ).build();
        return ResponseEntity.status(status).body(error);
    }
}

