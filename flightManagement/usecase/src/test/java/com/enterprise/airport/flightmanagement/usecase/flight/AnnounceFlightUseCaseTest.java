package com.enterprise.airport.flightmanagement.usecase.flight;

import com.enterprise.airport.flightmanagement.domain.aircraft.Aircraft;
import com.enterprise.airport.flightmanagement.domain.flight.FlightId;
import com.enterprise.airport.flightmanagement.usecase.Fixtures;
import com.enterprise.airport.flightmanagement.usecase.fake.AircraftPersisterFake;
import com.enterprise.airport.flightmanagement.usecase.fake.FlightPersisterFake;
import com.enterprise.airport.flightmanagement.usecase.rules.AircraftIsOccupiedByAnotherFlightImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class AnnounceFlightUseCaseTest {

    @Test
    void announceFlight() {
        var flightPersister = new FlightPersisterFake();
        var aircraftIsOccupiedByAnotherFlightRule = new AircraftIsOccupiedByAnotherFlightImpl(flightPersister);

        var aircraftPersister = new AircraftPersisterFake();

        Aircraft aircraft = Fixtures.newAircraft();
        aircraftPersister.save(aircraft);

        FlightId flightId = new FlightId(35L);

        var usecase = new AnnounceFlightUseCase(
                aircraftIsOccupiedByAnotherFlightRule,
                (airport, departureTime) -> true,
                () -> flightId,
                flightPersister,
                aircraftPersister
        );

        var request = new AnnounceFlightRequest(
                "AR",
                "YW",
                LocalDateTime.of(2021, 6, 24, 14, 45),
                aircraft.getId().getValue()
        );

        var savedFlightId = usecase.execute(request);

        Assertions.assertNotNull(savedFlightId);
        Assertions.assertEquals(flightId, savedFlightId);
        Assertions.assertTrue(flightPersister.data.containsKey(flightId));
    }

    @Test
    void throwsExceptionWhenAnnounceFlightForNotExistedAircraft() {
        var flightPersister = new FlightPersisterFake();
        var aircraftIsOccupiedByAnotherFlightRule = new AircraftIsOccupiedByAnotherFlightImpl(flightPersister);

        var aircraftPersister = new AircraftPersisterFake();
        FlightId flightId = new FlightId(35L);

        var usecase = new AnnounceFlightUseCase(
                aircraftIsOccupiedByAnotherFlightRule,
                (airport, departureTime) -> true,
                () -> flightId,
                flightPersister,
                aircraftPersister
        );

        var request = new AnnounceFlightRequest(
                "AR",
                "YW",
                LocalDateTime.of(2021, 6, 24, 14, 45),
                6548L
        );

        Assertions.assertThrows(AnnounceFlightUseCase.IllegalAircraftIdException.class, () -> usecase.execute(request));
    }
}