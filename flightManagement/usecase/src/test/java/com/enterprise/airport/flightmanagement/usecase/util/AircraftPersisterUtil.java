package com.enterprise.airport.flightmanagement.usecase.util;

import com.enterprise.airport.flightmanagement.domain.aircraft.Aircraft;
import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftId;
import com.enterprise.airport.flightmanagement.usecase.aircraft.AircraftPersister;

import java.util.HashMap;
import java.util.Map;

public class AircraftPersisterUtil implements AircraftPersister {

    public Map<AircraftId, Aircraft> data = new HashMap<>();

    @Override
    public void save(Aircraft aircraft) {
        data.put(aircraft.getId(), aircraft);
    }
}
