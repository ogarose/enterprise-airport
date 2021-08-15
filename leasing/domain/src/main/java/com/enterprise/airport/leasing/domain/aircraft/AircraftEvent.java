package com.enterprise.airport.leasing.domain.aircraft;

import com.enterprise.airport.common.types.domain.base.DomainEvent;
import lombok.EqualsAndHashCode;
import lombok.Value;

@SuppressWarnings("PMD.MissingStaticMethodInNonInstantiatableClass")
public class AircraftEvent {
    private AircraftEvent() {
    }

    @Value
    @EqualsAndHashCode(callSuper = false)
    public static class Bought extends DomainEvent {
        AircraftId aircraftId;
    }
}
