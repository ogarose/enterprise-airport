package com.enterprise.airport.flightmanagement.usecase.flight;

import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftId;
import com.enterprise.airport.flightmanagement.domain.flight.Flight;
import com.enterprise.airport.flightmanagement.domain.flight.FlightId;

import java.util.List;
import java.util.Optional;

public interface FlightExtractor {
    Optional<Flight> fidById(FlightId id);

    List<Flight> getAllByAircraftId(AircraftId id);
}
