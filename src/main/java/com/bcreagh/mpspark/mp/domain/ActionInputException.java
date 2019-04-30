package com.bcreagh.mpspark.mp.domain;

public class ActionInputException extends RuntimeException {
    public ActionInputException(String message) {
        super(message);
    }

    public ActionInputException(String message, Exception ex) {
        super(message, ex);
    }
}
