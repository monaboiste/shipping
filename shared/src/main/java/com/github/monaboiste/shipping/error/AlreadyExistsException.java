package com.github.monaboiste.shipping.error;

public class AlreadyExistsException extends TranslatableDomainException {

    public AlreadyExistsException(String errorKey, String debugMessage) {
        super(errorKey, debugMessage);
    }
}
