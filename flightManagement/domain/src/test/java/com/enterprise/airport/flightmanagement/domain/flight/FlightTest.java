package com.enterprise.airport.flightmanagement.domain.flight;

import com.enterprise.airport.common.types.common.Airport;
import com.enterprise.airport.flightmanagement.domain.Fixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class FlightTest {
    @Test
    void canNotAnnounceFlightForOccupiedAircraft() {
        Assertions.assertThrows(Flight.AircraftIsOccupiedByAnotherFlightException.class, () ->
                Flight.announceFlight(
                        (aircraftId) -> true,
                        (airport, departureTime) -> true,
                        () -> new FlightId(25L),
                        Airport.AC,
                        Airport.G9,
                        LocalDateTime.of(2021, 3, 10, 10, 55),
                        Fixtures.newAircraft()
                )
        );
    }

    @Test
    void canNotAnnounceFlightForNorAllowedDepartureTime() {
        Assertions.assertThrows(Flight.DepartureTimeIsNotAllowedException.class, () ->
                Flight.announceFlight(
                        (aircraftId) -> false,
                        (airport, departureTime) -> false,
                        () -> new FlightId(25L),
                        Airport.AC,
                        Airport.G9,
                        LocalDateTime.of(2021, 3, 10, 10, 55),
                        Fixtures.newAircraft()
                )
        );
    }

    @Test
    void announceFlight() {
        var announcedFlight = Flight.announceFlight(
                (aircraftId) -> false,
                (airport, departureTime) -> true,
                () -> new FlightId(25L),
                Airport.AC,
                Airport.G9,
                LocalDateTime.of(2021, 3, 10, 10, 55),
                Fixtures.newAircraft()
        );

        var eventList = announcedFlight.popEvents();
        Assertions.assertEquals(1, eventList.size());
        Assertions.assertEquals(FlightEvent.FlightAnnounced.class, eventList.get(0).getClass());

        var flightAnnouncedEvent = (FlightEvent.FlightAnnounced) eventList.get(0);
        Assertions.assertEquals(announcedFlight.getId(), flightAnnouncedEvent.getFlightId());
    }
}