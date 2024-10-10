package com.example.data.exception;

import org.springframework.http.HttpStatus;

public enum CustomHttpStatus {
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND),
    DUPLICATE_VALUE(HttpStatus.CONFLICT),
    INVALID_INPUT(HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
    FORBIDDEN(HttpStatus.FORBIDDEN),
    DATA_PROCESSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    EXTERNAL_SERVICE_ERROR(HttpStatus.BAD_GATEWAY),
    FILE_OPERATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    BUSINESS_LOGIC_ERROR(HttpStatus.UNPROCESSABLE_ENTITY),
    CONFIGURATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
    CONCURRENCY_ERROR(HttpStatus.CONFLICT);


    private final HttpStatus status;



    CustomHttpStatus(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
