package com.example.gorai.technology.solution.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Global exception handler to manage errors
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Declare the logger at the class level
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handles runtime exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
        logger.error("RuntimeException occurred: {}", e.getMessage(), e);  // Correctly logging the error message and stack trace
        return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    // Handles general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        logger.error("Exception occurred: {}", e.getMessage(), e);  // Correctly logging the error message and stack trace
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred: " + e.getMessage());
    }

    // Utility method to build structured error response
    private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String message) {
        ErrorResponse errorResponse = new ErrorResponse(status.value(), message);
        return ResponseEntity.status(status).body(errorResponse);
    }
    
    

    // Error response model
    public static class ErrorResponse {
        private int statusCode;
        private String message;

        public ErrorResponse(int statusCode, String message) {
            this.statusCode = statusCode;
            this.message = message;
        }

        // Getters and setters
        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
