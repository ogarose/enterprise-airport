package com.enterprise.airport.maintenance.usecase.flight;

import com.enterprise.airport.common.types.common.Airport;
import com.enterprise.airport.maintenance.domain.flight.FlightHours;
import com.enterprise.airport.maintenance.domain.flight.FlightId;
import lombok.Data;

@Data
public class FinishFlightRequest {
    private FlightId flightId;
    private Airport arrivedAirport;
    private FlightHours flightHours;

    public FinishFlightRequest(
            Long flightId,
            String arrivedAirport,
            Long flightHours
    ) {
        this.flightId = new FlightId(flightId);
        this.arrivedAirport = Airport.valueOf(arrivedAirport);
        this.flightHours = new FlightHours(flightHours);
    }
}
