package com.enterprise.airport.maintenance.usecase.flight;

import com.enterprise.airport.common.types.domain.common.Airport;
import com.enterprise.airport.maintenance.domain.flight.FlightId;
import lombok.Data;

@Data
public class RegisterFlightRequest {
    private FlightId id;
    private Airport departureAirport;

    public RegisterFlightRequest(Long id, String departureAirport) {
        this.id = new FlightId(id);
        this.departureAirport = Airport.valueOf(departureAirport);
    }
}
