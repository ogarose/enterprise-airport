package com.enterprise.airport.maintenance.usecase.flight;

import com.enterprise.airport.common.types.domain.common.Airport;
import com.enterprise.airport.maintenance.usecase.fake.FlightPersisterFake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegisterFlightUseCaseTest {

    private FlightPersisterFake flightPersister;

    @BeforeEach
    void beforeEach() {
        flightPersister = new FlightPersisterFake();
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