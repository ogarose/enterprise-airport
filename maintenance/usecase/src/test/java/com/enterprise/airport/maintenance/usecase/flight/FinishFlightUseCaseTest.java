package com.enterprise.airport.maintenance.usecase.flight;

import com.enterprise.airport.common.types.common.Airport;
import com.enterprise.airport.maintenance.domain.flight.Flight;
import com.enterprise.airport.maintenance.domain.flight.FlightStatus;
import com.enterprise.airport.maintenance.usecase.Fixtures;
import com.enterprise.airport.maintenance.usecase.util.FlightPersisterUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FinishFlightUseCaseTest {

    private FlightPersisterUtil flightPersisterUtil;

    @BeforeEach
    void beforeEach() {
        flightPersisterUtil = new FlightPersisterUtil();
    }

    @Test
    void finishFlight() {
        var usecase = new FinishFlightUseCase(
                flightPersisterUtil,
                flightPersisterUtil
        );

        Flight flight = Fixtures.generateRegisteredFlight();
        flightPersisterUtil.save(flight);

        var request = new FinishFlightRequest(
                flight.getId().getValue(),
                Airport.CA.name(),
                7L
        );

        usecase.execute(request);

        Assertions.assertEquals(FlightStatus.FINISHED, flight.getStatus());
        Assertions.assertEquals(request.getArrivedAirport(), flight.getArrivedAirport());
        Assertions.assertEquals(request.getFlightHours(), flight.getFlightHours());
    }

    @Test
    void throwsExceptionForNotExistedFlight() {
        var usecase = new FinishFlightUseCase(
                flightPersisterUtil,
                flightPersisterUtil
        );


        var request = new FinishFlightRequest(
                777L,
                Airport.CA.name(),
                7L
        );

        Assertions.assertThrows(FinishFlightUseCase.IllegalFlightIdException.class, () -> usecase.execute(request));
    }
}