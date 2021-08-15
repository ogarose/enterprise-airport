package com.enterprise.airport.maintenance.usecase;

import com.enterprise.airport.common.types.domain.common.Airport;
import com.enterprise.airport.maintenance.domain.flight.Flight;
import com.enterprise.airport.maintenance.domain.flight.FlightId;

import java.util.Random;

public class Fixtures {
    private static final Random RANDOM = new Random();

    public static Flight generateRegisteredFlight() {
        return Flight.register(
                new FlightId(RANDOM.nextLong()),
                Airport.TS
        );
    }
}
