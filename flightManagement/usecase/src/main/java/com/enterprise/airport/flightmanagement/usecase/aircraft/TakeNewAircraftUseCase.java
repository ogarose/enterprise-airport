package com.enterprise.airport.flightmanagement.usecase.aircraft;

import com.enterprise.airport.flightmanagement.domain.aircraft.Aircraft;
import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftId;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TakeNewAircraftUseCase implements TakeNewAircraft {

    private final AircraftPersister aircraftPersister;

    @Override
    public AircraftId execute(TakeNewAircraftRequest request) {
        var newAircraft = Aircraft.takeNewAircraft(
                request.getId(),
                request.getModel(),
                request.getSeatsCount()
        );

        aircraftPersister.save(newAircraft);

        return newAircraft.getId();
    }
}
