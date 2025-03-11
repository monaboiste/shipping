package com.github.monaboiste.shipping;

/**
 * @param twoLetterCode ISO 3166-1 alpha-2 country code
 * @param value phone text
 */
public record PhoneNumber(String twoLetterCode, String value) {
}
