package com.enterprise.airport.maintenance.usecase.flight;

import com.enterprise.airport.common.types.common.Airport;
import com.enterprise.airport.maintenance.domain.flight.Flight;
import com.enterprise.airport.maintenance.domain.flight.FlightStatus;
import com.enterprise.airport.maintenance.usecase.Fixtures;
import com.enterprise.airport.maintenance.usecase.fake.FlightPersisterFake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FinishFlightUseCaseTest {

    private FlightPersisterFake flightPersisterFake;

    @BeforeEach
    void beforeEach() {
        flightPersisterFake = new FlightPersisterFake();
    }

    @Test
    void finishFlight() {
        var flightPersisterSpy = new FlightPersister() {
            public Flight savedFlight;

            @Override
            public void save(Flight flight) {
                savedFlight = flight;
            }
        };

        var usecase = new FinishFlightUseCase(
                flightPersisterFake,
                flightPersisterSpy
        );

        Flight flight = Fixtures.generateRegisteredFlight();
        flightPersisterFake.save(flight);

        var request = new FinishFlightRequest(
                flight.getId().getValue(),
                Airport.CA.name(),
                7L
        );

        usecase.execute(request);

        Flight savedFlight = flightPersisterSpy.savedFlight;

        Assertions.assertNotNull(savedFlight);
        Assertions.assertEquals(FlightStatus.FINISHED, savedFlight.getStatus());
        Assertions.assertEquals(request.getArrivedAirport(), savedFlight.getArrivedAirport());
        Assertions.assertEquals(request.getFlightHours(), savedFlight.getFlightHours());
    }

    @Test
    void throwsExceptionForNotExistedFlight() {
        var usecase = new FinishFlightUseCase(
                flightPersisterFake,
                flightPersisterFake
        );


        var request = new FinishFlightRequest(
                777L,
                Airport.CA.name(),
                7L
        );

        Assertions.assertThrows(FinishFlightUseCase.IllegalFlightIdException.class, () -> usecase.execute(request));
    }
}