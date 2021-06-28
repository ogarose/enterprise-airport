package com.enterprise.airport.leasing.domain.aircraft;

import com.enterprise.airport.common.types.base.Version;

import java.time.LocalDate;

public final class AircraftRestorer {
    private AircraftRestorer() {
    }

    public static Aircraft restore(
            AircraftId id,
            Version version,
            AircraftMark mark,
            Double loadCapacity,
            LocalDate issueDate,
            AircraftLicenseNumber licenseNumber,
            AircraftSeats seats,
            AircraftContractNumber contractNumber
    ) {
        return new Aircraft(
                id,
                version,
                mark,
                loadCapacity,
                issueDate,
                licenseNumber,
                seats,
                contractNumber
        );
    }
}
