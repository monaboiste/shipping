package com.github.monaboiste.shipping.error;

public class NotFoundException extends DomainException {

    public NotFoundException(String errorKey, String debugMessage) {
        super(errorKey, debugMessage);
    }
}
