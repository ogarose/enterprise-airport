package com.enterprise.airport.leasing.domain.aircraft;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class AircraftContractNumberTest {

    @Test
    void createContractNumber() {
        String valueNumber = "9875/6548/D4";
        var contractNumber = new AircraftContractNumber(valueNumber);
        Assertions.assertEquals(valueNumber, contractNumber.getValue());
    }

    @Test
    void exceptionWhenEmptyNumber() {
        Assertions.assertThrows(
                AircraftContractNumber.EmptyException.class,
                () -> new AircraftContractNumber("")
        );
    }
}