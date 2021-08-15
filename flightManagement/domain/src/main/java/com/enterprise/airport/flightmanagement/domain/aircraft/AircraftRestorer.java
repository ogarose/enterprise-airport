package com.enterprise.airport.flightmanagement.domain.aircraft;

import com.enterprise.airport.common.types.domain.base.Version;
import com.enterprise.airport.common.types.domain.common.AircraftModel;

public final class AircraftRestorer {
    private AircraftRestorer() {
    }

    public static Aircraft restore(
            AircraftId id,
            Version version,
            AircraftModel model,
            Long seatsCount
    ) {
        return new Aircraft(
                id, version, model, seatsCount
        );
    }
}
