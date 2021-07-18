package com.enterprise.airport.flightmanagement.usecase.flight;

import com.enterprise.airport.flightmanagement.domain.flight.FlightId;
import com.enterprise.airport.flightmanagement.usecase.rules.AircraftIsOccupiedByAnotherFlightImpl;
import com.enterprise.airport.flightmanagement.usecase.util.FlightPersisterUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class AnnounceFlightUseCaseTest {

    private FlightPersisterUtil flightPersister;
    private AircraftIsOccupiedByAnotherFlightImpl aircraftIsOccupiedByAnotherFlightRule;

    @BeforeEach
    void beforeEach() {
        flightPersister = new FlightPersisterUtil();
        aircraftIsOccupiedByAnotherFlightRule = new AircraftIsOccupiedByAnotherFlightImpl(flightPersister);
    }

    @Test
    void announceFlight() {
        FlightId flightId = new FlightId(35L);

        var usecase = new AnnounceFlightUseCase(
                aircraftIsOccupiedByAnotherFlightRule,
                (airport, departureTime) -> true,
                () -> flightId,
                flightPersister
        );

        var request = new AnnounceFlightRequest(
                "AR",
                "YW",
                LocalDateTime.of(2021, 6, 24, 14, 45),
                654L
        );

        var savedFlightId = usecase.execute(request);

        Assertions.assertNotNull(savedFlightId);
        Assertions.assertEquals(flightId, savedFlightId);
        Assertions.assertTrue(flightPersister.data.containsKey(flightId));
    }
}