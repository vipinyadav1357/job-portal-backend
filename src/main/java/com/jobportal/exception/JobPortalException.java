package com.jobportal.exception;

public class JobPortalException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public JobPortalException(String msg) {
        super(msg);
    }
}
