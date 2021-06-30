package com.enterprise.airport.leasing.domain.aircraft;

import com.enterprise.airport.common.types.base.Version;

import java.time.LocalDate;

public final class AircraftRestorer {
    private AircraftRestorer() {
    }

    public static Aircraft restore(
            AircraftId id,
            Version version,
            AircraftModel mark,
            AircraftLoadCapacity aircraftLoadCapacity,
            LocalDate issueDate,
            AircraftRegistration licenseNumber,
            AircraftSeats seats,
            AircraftContractNumber contractNumber
    ) {
        return new Aircraft(
                id,
                version,
                mark,
                aircraftLoadCapacity,
                issueDate,
                licenseNumber,
                seats,
                contractNumber
        );
    }
}
