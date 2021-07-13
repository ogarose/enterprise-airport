package com.enterprise.airport.maintenance.domain.flight;

import com.enterprise.airport.common.types.base.Version;
import com.enterprise.airport.common.types.common.Airport;

public final class FlightRestorer {
    private FlightRestorer() {
    }

    public static Flight restore(
            FlightId id,
            Version first,
            Airport departureAirport,
            Airport arrivedAirport,
            FlightHours flightHours,
            FlightStatus status
    ) {
        return new Flight(id, first, departureAirport, arrivedAirport, flightHours, status);
    }
}
