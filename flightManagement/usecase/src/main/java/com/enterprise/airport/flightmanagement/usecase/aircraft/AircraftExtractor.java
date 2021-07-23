package com.enterprise.airport.flightmanagement.usecase.aircraft;

import com.enterprise.airport.flightmanagement.domain.aircraft.Aircraft;
import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftId;

import java.util.Optional;

public interface AircraftExtractor {
    Optional<Aircraft> findById(AircraftId id);
}
