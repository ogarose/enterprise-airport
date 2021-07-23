package com.enterprise.airport.flightmanagement.usecase.fake;

import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftId;
import com.enterprise.airport.flightmanagement.domain.flight.Flight;
import com.enterprise.airport.flightmanagement.domain.flight.FlightId;
import com.enterprise.airport.flightmanagement.usecase.flight.FlightExtractor;
import com.enterprise.airport.flightmanagement.usecase.flight.FlightPersister;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightPersisterFake implements FlightPersister, FlightExtractor {
    public Map<FlightId, Flight> data = new HashMap<>();

    @Override
    public void save(Flight flight) {
        data.put(flight.getId(), flight);
    }

    @Override
    public Optional<Flight> fidById(FlightId id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<Flight> getAllByAircraftId(AircraftId id) {
        return data.values().stream()
                .filter(flight -> flight.getAircraftId().equals(id))
                .collect(Collectors.toList());
    }
}
