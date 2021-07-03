package com.enterprise.airport.flightmanagement.domain.ticket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class PriceTest {
    @Test
    void getZeroPrice() {
        var price = Price.zero();

        Assertions.assertEquals("0.00", price.getValue().toPlainString());
    }

    @Test
    void fromBigDecimal() {
        var price = Price.from(BigDecimal.valueOf(6548L).setScale(2));

        Assertions.assertEquals("6548.00", price.getValue().toPlainString());
    }

    @Test
    void fromBigDecimalWithWrongScale() {
        Assertions.assertThrows(Price.WrongScaleException.class, () -> {
            Price.from(BigDecimal.valueOf(6548L).setScale(5));
        });
    }

    @Test
    void fromBigDecimalWithNegativeValue() {
        Assertions.assertThrows(Price.NegativeException.class, () -> {
            Price.from(BigDecimal.valueOf(-6548L).setScale(2));
        });
    }
}