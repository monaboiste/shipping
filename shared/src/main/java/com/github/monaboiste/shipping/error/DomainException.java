package com.github.monaboiste.shipping.error;

// todo: this is generic exception

public class DomainException extends RuntimeException {

    private final String errorKey;

    public DomainException(String debugMessage) {
        super(debugMessage);
        this.errorKey = "TODO";
    }
}
