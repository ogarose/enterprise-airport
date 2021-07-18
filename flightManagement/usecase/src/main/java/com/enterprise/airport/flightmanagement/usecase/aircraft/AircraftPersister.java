package com.enterprise.airport.flightmanagement.usecase.aircraft;


import com.enterprise.airport.flightmanagement.domain.aircraft.Aircraft;

public interface AircraftPersister {
    void save(Aircraft aircraft);
}
