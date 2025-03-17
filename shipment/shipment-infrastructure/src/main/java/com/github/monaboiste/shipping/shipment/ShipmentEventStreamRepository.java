package com.github.monaboiste.shipping.shipment;

import com.github.monaboiste.shipping.ShipmentId;

import java.util.Optional;

public interface ShipmentEventStreamRepository {

    Optional<ShipmentEventStream> findById(ShipmentId shipmentId);

    boolean existsById(ShipmentId id);

    void save(ShipmentEventStream stream);
}
