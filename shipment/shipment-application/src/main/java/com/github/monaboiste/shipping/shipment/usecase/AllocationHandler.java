package com.github.monaboiste.shipping.shipment.usecase;

import com.github.monaboiste.shipping.shipment.CarrierServiceId;
import com.github.monaboiste.shipping.shipment.ShipmentId;
import com.github.monaboiste.shipping.error.NotFoundException;
import com.github.monaboiste.shipping.shipment.Shipment;
import com.github.monaboiste.shipping.shipment.ShipmentReadRepository;
import com.github.monaboiste.shipping.shipment.ShipmentWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import static com.github.monaboiste.shipping.shipment.error.ShipmentApplicationErrorCodes.SHIPMENT_DOES_NOT_EXISTS;

@RequiredArgsConstructor
class AllocationHandler implements AllocateShipment, ReallocateShipment {

    private final PlatformTransactionManager transactionManager;

    private final ShipmentReadRepository shipmentReadRepository;
    private final ShipmentWriteRepository shipmentWriteRepository;

    @Override
    public void allocate(ShipmentId shipmentId, CarrierServiceId carrierServiceId) {
        var tx = new TransactionTemplate(transactionManager);
        tx.executeWithoutResult(_ -> {
            Shipment shipment = shipmentReadRepository.findById(shipmentId)
                    .orElseThrow(() -> new NotFoundException(SHIPMENT_DOES_NOT_EXISTS,
                            "Shipment %s does not exist.".formatted(shipmentId)));
            shipment.allocate(carrierServiceId);
            shipmentWriteRepository.save(shipment);
        });
    }

    @Override
    public void reallocate(ShipmentId shipmentId, CarrierServiceId carrierServiceId) {
        var tx = new TransactionTemplate(transactionManager);
        tx.executeWithoutResult(_ -> {
            Shipment shipment = shipmentReadRepository.findById(shipmentId)
                    .orElseThrow(() -> new NotFoundException(SHIPMENT_DOES_NOT_EXISTS,
                            "Shipment %s does not exist.".formatted(shipmentId)));
            shipment.reallocate(carrierServiceId);
            shipmentWriteRepository.save(shipment);
        });
    }
}
