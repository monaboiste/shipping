package com.github.monaboiste.shipping.shipment;

import java.util.Optional;

public interface ShipmentReadRepository {

    Optional<Shipment> findById(ShipmentId id);
}
