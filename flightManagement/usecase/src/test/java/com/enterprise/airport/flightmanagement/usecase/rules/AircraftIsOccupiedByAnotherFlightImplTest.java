package com.enterprise.airport.flightmanagement.usecase.rules;

import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftId;
import com.enterprise.airport.flightmanagement.usecase.Fixtures;
import com.enterprise.airport.flightmanagement.usecase.flight.FlightExtractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class AircraftIsOccupiedByAnotherFlightImplTest {

    @Mock
    private FlightExtractor flightExtractor;

    @Test
    void flightIsNotOccupied() {
        var impl = new AircraftIsOccupiedByAnotherFlightImpl(flightExtractor);

        Assertions.assertFalse(impl.check(new AircraftId(65L)));
    }

    @Test
    void flightIsOccupied() {
        AircraftId aircraftId = new AircraftId(65L);

        var impl = new AircraftIsOccupiedByAnotherFlightImpl(flightExtractor);

        Mockito.when(flightExtractor.getAllByAircraftId(aircraftId)).thenReturn(List.of(
                Fixtures.flightWithAircraftId(aircraftId)
        ));

        Assertions.assertTrue(impl.check(aircraftId));
    }
}