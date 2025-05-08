package com.webrise.testproject.exception;

public class RestApiException extends RuntimeException {

    public RestApiException(String message) {
        super(message, null, false, false);
    }
}
