package com.enterprise.airport.common.types.domain.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AircraftModelTest {

    @Test
    void testCorrectCode() {
        Assertions.assertEquals("777", AircraftModel.BOEING_777.getCode());
    }
}