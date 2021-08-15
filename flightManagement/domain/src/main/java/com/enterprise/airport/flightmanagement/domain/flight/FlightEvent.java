package com.enterprise.airport.flightmanagement.domain.flight;

import com.enterprise.airport.common.types.domain.base.DomainEvent;
import lombok.EqualsAndHashCode;
import lombok.Value;

@SuppressWarnings("PMD.MissingStaticMethodInNonInstantiatableClass")
public final class FlightEvent {
    private FlightEvent() {
    }

    @Value
    @EqualsAndHashCode(callSuper = true)
    public static class FlightAnnounced extends DomainEvent {
        FlightId flightId;
    }
}
