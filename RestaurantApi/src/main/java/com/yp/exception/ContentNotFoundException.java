package com.yp.exception;

public class ContentNotFoundException extends RuntimeException{

    public ContentNotFoundException(String message) {
        super(message);
    }
}
