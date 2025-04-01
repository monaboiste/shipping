package com.github.monaboiste.shipping.shipment;

/**
 * ISO 3166 country representation.
 *
 * @param name name of the country
 * @param twoLetterCode ISO 3166-1 alpha-2 code
 */
public record Country(String twoLetterCode, String name) {
}
