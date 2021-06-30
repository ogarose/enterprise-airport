package com.enterprise.airport.leasing.domain.aircraft;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AircraftRegistrationTest {

    @Test
    void createLicenseNumber() {
        var number = "N547RF";
        var licenseNumber = new AircraftRegistration(number);

        Assertions.assertEquals(number, licenseNumber.getValue());
    }

    @Test
    void exceptionWhenEmpty() {
        Assertions.assertThrows(AircraftRegistration.EmptyException.class, () -> new AircraftRegistration(""));
    }

    @Test
    void exceptionWhenWrongFormat() {
        Assertions.assertThrows(
                AircraftRegistration.WrongFormatException.class,
                () -> new AircraftRegistration("6548 sasf")
        );
    }
}