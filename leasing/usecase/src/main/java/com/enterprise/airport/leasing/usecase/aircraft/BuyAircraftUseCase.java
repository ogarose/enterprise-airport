package com.enterprise.airport.leasing.usecase.aircraft;

import com.enterprise.airport.leasing.domain.aircraft.Aircraft;
import com.enterprise.airport.leasing.domain.aircraft.AircraftId;
import com.enterprise.airport.leasing.domain.aircraft.AircraftIdGenerator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuyAircraftUseCase implements BuyAircraft {

    private final AircraftIdGenerator idGenerator;
    private final AircraftPersister aircraftPersister;

    @Override
    public AircraftId execute(BuyAircraftRequest request) {
        var boughtAircraft = Aircraft.buy(
                idGenerator,
                request.getModel(),
                request.getAircraftLoadCapacity(),
                request.getIssueDate(),
                request.getRegistrationNumber(),
                request.getSeats(),
                request.getContractNumber()
        );

        aircraftPersister.save(boughtAircraft);

        return boughtAircraft.getId();
    }
}
