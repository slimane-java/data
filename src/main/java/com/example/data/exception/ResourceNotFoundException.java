package com.example.data.exception;

public class ResourceNotFoundException extends RuntimeException implements CustomHttpStatusException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    @Override
    public CustomHttpStatus getStatus() {
        return CustomHttpStatus.RESOURCE_NOT_FOUND;
    }
}
