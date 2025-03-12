package com.github.monaboiste.shipping.error;

public class DomainException extends RuntimeException {

    private final String errorKey;

    public DomainException(String errorKey, String debugMessage) {
        super(debugMessage);
        this.errorKey = errorKey;
    }
}
