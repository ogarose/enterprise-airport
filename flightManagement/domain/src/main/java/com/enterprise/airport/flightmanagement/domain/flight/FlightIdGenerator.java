package com.enterprise.airport.flightmanagement.domain.flight;

@FunctionalInterface
public interface FlightIdGenerator {
    FlightId generate();
}
