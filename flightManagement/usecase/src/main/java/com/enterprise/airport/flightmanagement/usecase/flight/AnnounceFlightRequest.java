package com.enterprise.airport.flightmanagement.usecase.flight;

import com.enterprise.airport.common.types.common.Airport;
import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnnounceFlightRequest {
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime departureTime;
    private AircraftId aircraftId;

    public AnnounceFlightRequest(
            String departureAirport,
            String arrivalAirport,
            LocalDateTime departureTime,
            Long aircraftId
    ) {
        this.departureAirport = Airport.valueOf(departureAirport);
        this.arrivalAirport = Airport.valueOf(arrivalAirport);
        this.departureTime = departureTime;
        this.aircraftId = new AircraftId(aircraftId);
    }
}
