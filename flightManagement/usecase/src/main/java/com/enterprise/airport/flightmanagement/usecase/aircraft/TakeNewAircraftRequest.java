package com.enterprise.airport.flightmanagement.usecase.aircraft;

import com.enterprise.airport.common.types.common.AircraftModel;
import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftId;
import lombok.Data;

@Data
public class TakeNewAircraftRequest {
    private AircraftId id;
    private AircraftModel model;
    private Long seatsCount;

    public TakeNewAircraftRequest(
            Long id,
            String model,
            Long seatsCount
    ) {
        this.id = new AircraftId(id);
        this.model = AircraftModel.valueOf(model);
        this.seatsCount = seatsCount;
    }
}
