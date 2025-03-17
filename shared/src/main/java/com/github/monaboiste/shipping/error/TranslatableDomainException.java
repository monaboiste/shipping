package com.github.monaboiste.shipping.error;

public abstract class TranslatableDomainException extends RuntimeException {

    private final String errorKey;

    protected TranslatableDomainException(String errorKey, String debugMessage) {
        super(debugMessage);
        this.errorKey = errorKey;
    }
}
