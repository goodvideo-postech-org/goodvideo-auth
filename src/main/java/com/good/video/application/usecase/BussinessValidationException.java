package com.good.video.application.usecase;

public class BussinessValidationException extends RuntimeException {

    public BussinessValidationException(String message) {
        super(message);
    }

    public BussinessValidationException(String message, Throwable e) {
        super(message, e);
    }

}