package com.enterprise.airport.leasing.domain.aircraft;

import com.enterprise.airport.common.types.base.AggregateRoot;
import com.enterprise.airport.common.types.base.Version;
import com.enterprise.airport.common.types.common.AircraftModel;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Aircraft extends AggregateRoot<AircraftId> {
    private AircraftModel model;
    private AircraftLoadCapacity aircraftLoadCapacity;
    private LocalDate issueDate;
    private AircraftRegistration registration;
    private AircraftSeats seats;
    private AircraftContractNumber contractNumber;

    protected Aircraft(
            AircraftId id,
            Version version,
            AircraftModel model,
            AircraftLoadCapacity aircraftLoadCapacity,
            LocalDate issueDate,
            AircraftRegistration registration,
            AircraftSeats seats,
            AircraftContractNumber contractNumber
    ) {
        super(id, version);
        this.model = model;
        this.aircraftLoadCapacity = aircraftLoadCapacity;
        this.issueDate = issueDate;
        this.registration = registration;
        this.seats = seats;
        this.contractNumber = contractNumber;
    }

    public static Aircraft buy(
            AircraftIdGenerator idGenerator,
            AircraftModel model,
            AircraftLoadCapacity aircraftLoadCapacity,
            LocalDate issueDate,
            AircraftRegistration registration,
            AircraftSeats seats,
            AircraftContractNumber contractNumber
    ) {
        var aircraft = new Aircraft(
                idGenerator.generate(),
                Version.first(),
                model,
                aircraftLoadCapacity,
                issueDate,
                registration,
                seats,
                contractNumber
        );

        aircraft.addEvent(new AircraftEvent.Bought(aircraft.getId()));

        return aircraft;
    }
}
