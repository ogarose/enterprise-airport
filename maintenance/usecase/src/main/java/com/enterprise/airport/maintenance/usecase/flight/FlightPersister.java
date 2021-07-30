package com.enterprise.airport.maintenance.usecase.flight;

import com.enterprise.airport.maintenance.domain.flight.Flight;

public interface FlightPersister {
    void save(Flight flight);
}
