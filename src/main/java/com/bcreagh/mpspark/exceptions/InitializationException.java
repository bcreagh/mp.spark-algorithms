package com.bcreagh.mpspark.exceptions;

public class InitializationException extends RuntimeException {

    public InitializationException(String message, Exception cause) {
        super(message, cause);
    }
}
