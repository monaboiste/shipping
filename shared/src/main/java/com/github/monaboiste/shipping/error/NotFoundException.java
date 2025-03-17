package com.github.monaboiste.shipping.error;

public class NotFoundException extends TranslatableDomainException {

    public NotFoundException(String errorKey, String debugMessage) {
        super(errorKey, debugMessage);
    }
}
