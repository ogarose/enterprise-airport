package com.enterprise.airport.flightmanagement.domain.aircraft;

import com.enterprise.airport.common.types.domain.base.DomainEvent;
import lombok.EqualsAndHashCode;
import lombok.Value;

@SuppressWarnings("PMD.MissingStaticMethodInNonInstantiatableClass")
public class AircraftEvent {
    private AircraftEvent() {
    }

    @Value
    @EqualsAndHashCode(callSuper = true)
    public static class NewAircraftTaken extends DomainEvent {
        AircraftId aircraftId;
    }
}
