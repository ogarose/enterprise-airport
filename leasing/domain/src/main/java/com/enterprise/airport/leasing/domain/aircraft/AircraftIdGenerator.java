package com.enterprise.airport.leasing.domain.aircraft;

@FunctionalInterface
public interface AircraftIdGenerator {
    AircraftId generate();
}
