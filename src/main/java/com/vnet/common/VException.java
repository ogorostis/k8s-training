package com.vnet.common;

public class VException extends RuntimeException {
    public VException(final String message) {
        super(message);
    }

    public VException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
