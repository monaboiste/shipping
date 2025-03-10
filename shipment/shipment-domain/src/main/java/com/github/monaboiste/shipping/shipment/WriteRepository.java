package com.github.monaboiste.shipping.shipment;

public interface WriteRepository<I, T> {

    void save(T t);
}
