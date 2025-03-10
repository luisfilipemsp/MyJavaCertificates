package com.lfmsp.exception;

public class CertificateAccessException extends RuntimeException {
    public CertificateAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}