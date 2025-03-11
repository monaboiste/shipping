package com.github.monaboiste.shipping.error;

public class NotFoundException extends DomainException {

    public NotFoundException(String debugMessage) {
        super(debugMessage);
    }
}
