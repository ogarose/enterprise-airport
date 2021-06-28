package com.enterprise.airport.leasing.domain.aircraft;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AircraftLicenseNumberTest {

    @Test
    void createLicenseNumber() {
        var number = "654 5 dt";
        var licenseNumber = new AircraftLicenseNumber(number);

        Assertions.assertEquals(number, licenseNumber.getValue());
    }

    @Test
    void exceptionWhenEmpty() {
        Assertions.assertThrows(AircraftLicenseNumber.EmptyException.class, () -> new AircraftLicenseNumber(""));
    }

    @Test
    void exceptionWhenWrongFormat() {
        Assertions.assertThrows(
                AircraftLicenseNumber.WrongFormatException.class,
                () -> new AircraftLicenseNumber("6548 sasf")
        );
    }
}