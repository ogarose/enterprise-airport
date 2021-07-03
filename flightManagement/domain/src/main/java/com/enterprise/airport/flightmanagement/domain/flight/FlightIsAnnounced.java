package com.enterprise.airport.flightmanagement.domain.flight;

public interface FlightIsAnnounced {
    default boolean check(Flight flight) {
        return flight != null;
    }
}
