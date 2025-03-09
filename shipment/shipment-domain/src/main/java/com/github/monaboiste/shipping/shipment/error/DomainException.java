package com.github.monaboiste.shipping.shipment.error;

public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }
}
