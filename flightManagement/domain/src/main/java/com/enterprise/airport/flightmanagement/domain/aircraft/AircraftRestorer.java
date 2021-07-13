package com.enterprise.airport.flightmanagement.domain.aircraft;

import com.enterprise.airport.common.types.base.Version;
import com.enterprise.airport.common.types.common.AircraftModel;

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
