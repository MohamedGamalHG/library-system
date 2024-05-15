package com.example.LibraryManagementSystem.exceptionHandling;

import org.springframework.stereotype.Component;

@Component
public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
