package com.github.monaboiste.shipping.shipment;

import java.util.Optional;

public interface ReadRepository<I, T> {

    Optional<T> findById(I id);
}
