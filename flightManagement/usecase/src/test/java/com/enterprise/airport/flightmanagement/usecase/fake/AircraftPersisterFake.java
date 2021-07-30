package com.enterprise.airport.flightmanagement.usecase.fake;

import com.enterprise.airport.flightmanagement.domain.aircraft.Aircraft;
import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftId;
import com.enterprise.airport.flightmanagement.usecase.aircraft.AircraftExtractor;
import com.enterprise.airport.flightmanagement.usecase.aircraft.AircraftPersister;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AircraftPersisterFake implements AircraftPersister, AircraftExtractor {

    public Map<AircraftId, Aircraft> data = new HashMap<>();

    @Override
    public void save(Aircraft aircraft) {
        data.put(aircraft.getId(), aircraft);
    }

    @Override
    public Optional<Aircraft> findById(AircraftId id) {
        return Optional.ofNullable(data.get(id));
    }
}
