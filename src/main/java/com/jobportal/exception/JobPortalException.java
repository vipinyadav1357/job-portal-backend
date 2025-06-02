package com.jobportal.exception;

import java.io.Serial;

public class JobPortalException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public JobPortalException(String msg) {
        super(msg);
    }
}
