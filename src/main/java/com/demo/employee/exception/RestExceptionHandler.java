package com.demo.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String reason = ex.getReason();
        String message = ex.getMessage();
        String developerMessage = "Additional information for developers"; // Add any developer-specific message

        ErrorResponse errorResponse = new ErrorResponse(status.value(), reason, message, developerMessage);
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleGenericException(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Internal Server Error";
        String developerMessage = "Additional information for developers";

        ErrorResponse errorResponse = new ErrorResponse(status.value(), ex.getMessage(), message, developerMessage);
        return new ResponseEntity<>(errorResponse, status);
    }


}
