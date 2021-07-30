package com.enterprise.airport.maintenance.usecase.fake;

import com.enterprise.airport.maintenance.domain.flight.Flight;
import com.enterprise.airport.maintenance.domain.flight.FlightId;
import com.enterprise.airport.maintenance.usecase.flight.FlightExtractor;
import com.enterprise.airport.maintenance.usecase.flight.FlightPersister;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FlightPersisterFake implements FlightPersister, FlightExtractor {
    public Map<FlightId, Flight> data = new HashMap<>();

    @Override
    public void save(Flight flight) {
        data.put(flight.getId(), flight);
    }

    @Override
    public Optional<Flight> getById(FlightId flightId) {
        return Optional.ofNullable(data.get(flightId));
    }
}
