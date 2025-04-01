package com.github.monaboiste.shipping.shipment.api.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ShipmentApi {

    @GetMapping("/test")
    String test() {
        return "It works";
    }
}
