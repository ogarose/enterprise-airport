package com.enterprise.airport.flightmanagement.usecase.flight;

import com.enterprise.airport.flightmanagement.domain.flight.Flight;

public interface FlightPersister {
    void save(Flight flight);
}
