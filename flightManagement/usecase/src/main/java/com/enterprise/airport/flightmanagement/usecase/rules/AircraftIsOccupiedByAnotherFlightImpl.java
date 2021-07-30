package com.enterprise.airport.flightmanagement.usecase.rules;

import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftId;
import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftIsOccupiedByAnotherFlight;
import com.enterprise.airport.flightmanagement.usecase.flight.FlightExtractor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AircraftIsOccupiedByAnotherFlightImpl implements AircraftIsOccupiedByAnotherFlight {
    private final FlightExtractor flightExtractor;

    @Override
    public boolean check(AircraftId aircraftId) {
        return !flightExtractor.getAllByAircraftId(aircraftId).isEmpty();
    }
}
