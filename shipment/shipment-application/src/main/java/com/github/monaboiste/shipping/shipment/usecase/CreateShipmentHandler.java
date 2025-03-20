package com.github.monaboiste.shipping.shipment.usecase;

import com.github.monaboiste.shipping.shipment.Shipment;
import com.github.monaboiste.shipping.shipment.ShipmentWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
class CreateShipmentHandler implements CreateShipment {

    private final PlatformTransactionManager transactionManager;

    private final ShipmentWriteRepository shipmentWriteRepository;

    @Override
    public void create(Shipment shipment) {
        var tx = new TransactionTemplate(transactionManager);
        tx.executeWithoutResult(_ -> shipmentWriteRepository.save(shipment));
    }
}
