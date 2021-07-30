package com.enterprise.airport.flightmanagement.usecase.aircraft;

import com.enterprise.airport.flightmanagement.usecase.fake.AircraftPersisterFake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TakeNewAircraftUseCaseTest {

    private final AircraftPersisterFake aircraftPersister = new AircraftPersisterFake();

    @Test
    void takeNewAircraft() {
        var usecase = new TakeNewAircraftUseCase(aircraftPersister);

        var request = new TakeNewAircraftRequest(
                6587L,
                "AIRBUS_A320",
                64L
        );

        var aircraftId = usecase.execute(request);

        Assertions.assertEquals(request.getId(), aircraftId);
        Assertions.assertNotNull(aircraftPersister.data.get(request.getId()));
    }
}