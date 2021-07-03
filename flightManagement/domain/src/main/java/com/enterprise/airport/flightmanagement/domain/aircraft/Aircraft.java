package com.enterprise.airport.flightmanagement.domain.aircraft;

import com.enterprise.airport.common.types.base.AggregateRoot;
import com.enterprise.airport.common.types.base.Version;
import com.enterprise.airport.common.types.common.AircraftModel;
import lombok.Getter;

@Getter
public class Aircraft extends AggregateRoot<AircraftId> {
    private AircraftModel model;
    private Long seatsCount;

    protected Aircraft(
            AircraftId id,
            Version version,
            AircraftModel model,
            Long seatsCount
    ) {
        super(id, version);
        this.model = model;
        this.seatsCount = seatsCount;
    }

    public static Aircraft takeNewAircraft(
            AircraftId id,
            AircraftModel mark,
            Long seatsCount
    ) {
        var aircraft = new Aircraft(id, Version.first(), mark, seatsCount);
        aircraft.addEvent(new AircraftEvent.NewAircraftTaken(aircraft.getId()));

        return aircraft;
    }
}
