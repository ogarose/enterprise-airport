package com.enterprise.airport.leasing.domain.aircraft;

import com.enterprise.airport.common.types.base.AggregateRoot;
import com.enterprise.airport.common.types.base.Version;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Aircraft extends AggregateRoot<AircraftId> {
    private AircraftMark mark;
    private Double loadCapacity;
    private LocalDate issueDate;
    private AircraftLicenseNumber licenseNumber;
    private AircraftSeats seats;
    private AircraftContractNumber contractNumber;

    protected Aircraft(
            AircraftId id,
            Version version,
            AircraftMark mark,
            Double loadCapacity,
            LocalDate issueDate,
            AircraftLicenseNumber licenseNumber,
            AircraftSeats seats,
            AircraftContractNumber contractNumber
    ) {
        super(id, version);
        this.mark = mark;
        this.loadCapacity = loadCapacity;
        this.issueDate = issueDate;
        this.licenseNumber = licenseNumber;
        this.seats = seats;
        this.contractNumber = contractNumber;
    }

    public static Aircraft buy(
            AircraftIdGenerator idGenerator,
            AircraftMark mark,
            Double loadCapacity,
            LocalDate issueDate,
            AircraftLicenseNumber licenseNumber,
            AircraftSeats seats,
            AircraftContractNumber contractNumber
    ) {
        var aircraft = new Aircraft(
                idGenerator.generate(),
                Version.first(),
                mark,
                loadCapacity,
                issueDate,
                licenseNumber,
                seats,
                contractNumber
        );

        aircraft.addEvent(new AircraftEvent.Bought(aircraft.getId()));

        return aircraft;
    }
}
