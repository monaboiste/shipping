package com.github.monaboiste.shipping.shipment.usecase;

import com.github.monaboiste.shipping.error.NotFoundException;
import com.github.monaboiste.shipping.shipment.Shipment;
import com.github.monaboiste.shipping.ShipmentId;
import com.github.monaboiste.shipping.shipment.ShipmentReadRepository;
import com.github.monaboiste.shipping.shipment.ShipmentWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import static com.github.monaboiste.shipping.shipment.error.ShipmentApplicationErrorCodes.SHIPMENT_DOES_NOT_EXISTS;

@RequiredArgsConstructor
class DeallocationHandler implements DeallocateShipment {

    private final PlatformTransactionManager transactionManager;

    private final ShipmentReadRepository shipmentReadRepository;
    private final ShipmentWriteRepository shipmentWriteRepository;

    @Override
    public void deallocate(ShipmentId shipmentId) {
        TransactionTemplate tx = new TransactionTemplate(transactionManager);
        tx.executeWithoutResult(_ -> {
            Shipment shipment = shipmentReadRepository.findById(shipmentId)
                    .orElseThrow(() -> new NotFoundException(SHIPMENT_DOES_NOT_EXISTS,
                            "Shipment %s does not exist.".formatted(shipmentId)));
            shipment.deallocate();

            shipmentWriteRepository.save(shipment);
        });
    }
}
