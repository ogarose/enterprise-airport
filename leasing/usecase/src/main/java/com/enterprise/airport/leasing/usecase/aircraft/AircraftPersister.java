package com.enterprise.airport.leasing.usecase.aircraft;

import com.enterprise.airport.leasing.domain.aircraft.Aircraft;

public interface AircraftPersister {
    void save(Aircraft aircraft);
}
