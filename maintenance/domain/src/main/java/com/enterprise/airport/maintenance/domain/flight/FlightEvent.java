package com.enterprise.airport.maintenance.domain.flight;

import com.enterprise.airport.common.types.domain.base.DomainEvent;
import lombok.EqualsAndHashCode;
import lombok.Value;

@SuppressWarnings("PMD.MissingStaticMethodInNonInstantiatableClass")
public class FlightEvent {
    private FlightEvent() {
    }

    @Value
    @EqualsAndHashCode(callSuper = true)
    public static class FlightRegistered extends DomainEvent {
        FlightId flightId;
    }

    @Value
    @EqualsAndHashCode(callSuper = true)
    public static class FlightArrived extends DomainEvent {
        FlightId flightId;
    }
}
