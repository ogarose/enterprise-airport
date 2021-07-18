package com.enterprise.airport.flightmanagement.usecase.aircraft;

import com.enterprise.airport.flightmanagement.usecase.util.AircraftPersisterUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TakeNewAircraftUseCaseTest {

    private final AircraftPersisterUtil aircraftPersister = new AircraftPersisterUtil();

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
    }
}