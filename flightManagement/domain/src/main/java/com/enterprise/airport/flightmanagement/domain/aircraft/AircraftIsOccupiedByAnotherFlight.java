package com.enterprise.airport.flightmanagement.domain.aircraft;

@FunctionalInterface
public interface AircraftIsOccupiedByAnotherFlight {
    boolean check(AircraftId aircraftId);
}
