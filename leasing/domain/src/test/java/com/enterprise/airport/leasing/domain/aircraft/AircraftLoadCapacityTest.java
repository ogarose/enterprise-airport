package com.enterprise.airport.leasing.domain.aircraft;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AircraftLoadCapacityTest {

    @Test
    void createLoadCapacity() {
        double expectedValue = 889_789.45;
        var loadCapacity = new AircraftLoadCapacity(expectedValue);

        Assertions.assertEquals(expectedValue, loadCapacity.getValue());
    }

    @Test
    void throwsExceptionForNull() {
        Assertions.assertThrows(AircraftLoadCapacity.EmptyException.class, () ->
            new AircraftLoadCapacity(null)
        );
    }
}