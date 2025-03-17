package com.github.monaboiste.shipping.shipment.usecase;

import com.github.monaboiste.shipping.error.AlreadyExistsException;
import com.github.monaboiste.shipping.shipment.Shipment;
import com.github.monaboiste.shipping.shipment.ShipmentReadRepository;
import com.github.monaboiste.shipping.shipment.ShipmentWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import static com.github.monaboiste.shipping.shipment.error.ShipmentApplicationErrorCodes.SHIPMENT_ALREADY_EXISTS;

@RequiredArgsConstructor
class CreateShipmentHandler implements CreateShipment {

    private final PlatformTransactionManager transactionManager;

    private final ShipmentReadRepository shipmentReadRepository;
    private final ShipmentWriteRepository shipmentWriteRepository;

    @Override
    public void create(Shipment shipment) {
        var tx = new TransactionTemplate(transactionManager);
        tx.executeWithoutResult(_ -> {
            boolean alreadyExists = shipmentReadRepository.existsById(shipment.id());
            if (alreadyExists) {
                throw new AlreadyExistsException(SHIPMENT_ALREADY_EXISTS,
                        "Shipment %s cannot be created as it already exists.".formatted(shipment.id()));
            }
            shipmentWriteRepository.save(shipment);
        });
    }
}
