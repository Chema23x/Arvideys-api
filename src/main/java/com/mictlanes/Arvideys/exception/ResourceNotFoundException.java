package com.mictlanes.Arvideys.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// This is an example for an Exception thrown by multiple services
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    // You can also add a constructor to pass in the cause of the exception
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // If you need to pass parameters like id, you can create a more specific message
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
