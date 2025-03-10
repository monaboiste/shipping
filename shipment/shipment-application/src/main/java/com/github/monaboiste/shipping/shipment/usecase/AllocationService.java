package com.github.monaboiste.shipping.shipment.usecase;

import com.github.monaboiste.shipping.shipment.CarrierServiceId;
import com.github.monaboiste.shipping.shipment.Shipment;
import com.github.monaboiste.shipping.shipment.ShipmentId;
import com.github.monaboiste.shipping.shipment.ShipmentReadRepository;
import com.github.monaboiste.shipping.shipment.ShipmentWriteRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AllocationService implements AllocateShipment {

    private final ShipmentReadRepository shipmentReadRepository;
    private final ShipmentWriteRepository shipmentWriteRepository;

    @Override
    public void allocate(ShipmentId shipmentId, CarrierServiceId carrierServiceId) {
        // TODO: do in transaction
//        var tx = new TransactionTemplate(transactionManager);
//        tx.executeWithoutResult(status -> {
            Shipment shipment = shipmentReadRepository.findById(shipmentId)
                    .orElseThrow();// TODO: error handling
            shipment.allocate(carrierServiceId);
            shipmentWriteRepository.save(shipment);
//        });
    }
}
