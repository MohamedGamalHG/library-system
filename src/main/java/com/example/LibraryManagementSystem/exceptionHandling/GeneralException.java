package com.example.LibraryManagementSystem.exceptionHandling;

import org.springframework.stereotype.Component;

@Component
public class GeneralException extends RuntimeException{
    public GeneralException() {
        super();
    }

    public GeneralException(String message) {
        super(message);
    }
}
