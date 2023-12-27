package com.demo.employee.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private final int status;
    private final String reason;
    private final String message;
    private final String developerMessage;

    public ErrorResponse(int status, String reason, String message, String developerMessage) {
        this.status = status;
        this.reason = reason;
        this.message = message;
        this.developerMessage = developerMessage;
    }


}