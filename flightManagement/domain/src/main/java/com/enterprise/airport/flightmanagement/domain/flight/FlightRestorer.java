package com.enterprise.airport.flightmanagement.domain.flight;

import com.enterprise.airport.common.types.domain.base.Version;
import com.enterprise.airport.common.types.domain.common.Airport;
import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftId;

import java.time.LocalDateTime;

public final class FlightRestorer {
    private FlightRestorer() {
    }

    public static Flight restore(
            FlightId id,
            Version first,
            Airport departureAirport,
            Airport arrivalAirport,
            LocalDateTime departureTime,
            AircraftId aircraftId
    ) {
        return new Flight(
                id, first, departureAirport, arrivalAirport, departureTime, aircraftId
        );
    }
}
