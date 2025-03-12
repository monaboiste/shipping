package com.github.monaboiste.shipping.shipment.usecase;

import com.github.monaboiste.shipping.shipment.Shipment;
import com.github.monaboiste.shipping.ShipmentId;
import com.github.monaboiste.shipping.shipment.ShipmentReadRepository;
import com.github.monaboiste.shipping.shipment.ShipmentWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
class DeallocationService implements DeallocateShipment {

    private final PlatformTransactionManager transactionManager;

    private final ShipmentReadRepository shipmentReadRepository;
    private final ShipmentWriteRepository shipmentWriteRepository;

    @Override
    public void deallocate(ShipmentId shipmentId) {
        TransactionTemplate tx = new TransactionTemplate(transactionManager);
        tx.executeWithoutResult(_ -> {
            Shipment shipment = shipmentReadRepository.findById(shipmentId)
                    .orElseThrow();
            shipment.deallocate();

            shipmentWriteRepository.save(shipment);
        });
    }
}
