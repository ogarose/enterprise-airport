package com.enterprise.airport.maintenance.usecase.flight;

import com.enterprise.airport.common.types.common.Airport;
import com.enterprise.airport.maintenance.usecase.util.FlightPersisterUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegisterFlightUseCaseTest {

    private FlightPersisterUtil flightPersister;

    @BeforeEach
    void beforeEach() {
        flightPersister = new FlightPersisterUtil();
    }

    @Test
    void registerFlight() {
        var usecase = new RegisterFlightUseCase(flightPersister);

        var request = new RegisterFlightRequest(
                8L,
                Airport.AF.name()
        );

        usecase.execute(request);

        Assertions.assertTrue(flightPersister.data.containsKey(request.getId()));
    }
}