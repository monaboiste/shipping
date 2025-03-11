package com.github.monaboiste.shipping.error;

public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }
}
