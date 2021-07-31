package com.enterprise.airport.maintenance.domain.flight;

import com.enterprise.airport.common.types.base.Version;
import com.enterprise.airport.common.types.common.Airport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FlightTest {

    @Test
    void registerFlight() {
        FlightId flightId = new FlightId(357L);

        var registeredFlight = Flight.register(
                flightId,
                Airport.NZ
        );

        Assertions.assertEquals(flightId, registeredFlight.getId());

        var events = registeredFlight.popEvents();
        Assertions.assertEquals(1, events.size());
        Assertions.assertEquals(FlightEvent.FlightRegistered.class, events.get(0).getClass());

        var flightRegisteredEvent = (FlightEvent.FlightRegistered) events.get(0);
        Assertions.assertEquals(registeredFlight.getId(), flightRegisteredEvent.getFlightId());
    }

    @Test
    void finishFlight() {
        var registeredFlight = FlightRestorer.restore(
                new FlightId(854L),
                Version.from(654L),
                Airport.A3,
                null,
                FlightHours.zero(),
                FlightStatus.REGISTER
        );

        registeredFlight.finish(
                Airport.NZ,
                FlightHours.from(7L).getOrNull()
        );

        Assertions.assertEquals(Airport.NZ, registeredFlight.getArrivedAirport());
        Assertions.assertEquals(FlightHours.from(7L).getOrNull(), registeredFlight.getFlightHours());

        var events = registeredFlight.popEvents();
        Assertions.assertEquals(1, events.size());
        Assertions.assertEquals(FlightEvent.FlightArrived.class, events.get(0).getClass());

        var flightArrivedEvent = (FlightEvent.FlightArrived) events.get(0);
        Assertions.assertEquals(registeredFlight.getId(), flightArrivedEvent.getFlightId());
    }
}