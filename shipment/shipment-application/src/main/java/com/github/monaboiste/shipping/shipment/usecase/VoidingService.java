package com.github.monaboiste.shipping.shipment.usecase;

import com.github.monaboiste.shipping.shipment.Shipment;
import com.github.monaboiste.shipping.ShipmentId;
import com.github.monaboiste.shipping.shipment.ShipmentReadRepository;
import com.github.monaboiste.shipping.shipment.ShipmentWriteRepository;
import com.github.monaboiste.shipping.shipment.usecase.VoidShipment;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
class VoidingService implements VoidShipment {

    private final PlatformTransactionManager transactionManager;

    private final ShipmentReadRepository shipmentReadRepository;
    private final ShipmentWriteRepository shipmentWriteRepository;

    @Override
    public void cancel(ShipmentId shipmentId) {
        TransactionTemplate tx = new TransactionTemplate(transactionManager);
        tx.executeWithoutResult(_ -> {
            Shipment shipment = shipmentReadRepository.findById(shipmentId)
                    .orElseThrow();
            shipment.cancel();

            shipmentWriteRepository.save(shipment);
        });
    }
}
