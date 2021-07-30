package com.enterprise.airport.maintenance.usecase.flight;

import com.enterprise.airport.maintenance.domain.flight.Flight;
import com.enterprise.airport.maintenance.domain.flight.FlightId;

import java.util.Optional;

public interface FlightExtractor {
    Optional<Flight> getById(FlightId flightId);
}
