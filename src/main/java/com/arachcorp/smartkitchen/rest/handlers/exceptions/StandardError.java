package com.arachcorp.smartkitchen.rest.handlers.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class StandardError {
    private String message;
    private List<String> errors;
    private int code;
    private String path;
    private LocalDateTime timestamp;
}
