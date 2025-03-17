package com.github.monaboiste.shipping.error;

public class CannotBeEmptyException extends TranslatableDomainException {

    public CannotBeEmptyException(String errorKey) {
        super(errorKey, "");
    }

    public CannotBeEmptyException(String errorKey, String debugMessage) {
        super(errorKey, debugMessage);
    }
}
