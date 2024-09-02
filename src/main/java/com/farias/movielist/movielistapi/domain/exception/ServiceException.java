package com.farias.movielist.movielistapi.domain.exception;

public class ServiceException extends RuntimeException {

    public ServiceException(String message, Throwable e) {
        super(message, e);
    }

    public ServiceException(String message) {
        super(message);
    }
}
