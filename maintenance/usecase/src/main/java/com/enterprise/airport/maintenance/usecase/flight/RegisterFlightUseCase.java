package com.enterprise.airport.maintenance.usecase.flight;

import com.enterprise.airport.maintenance.domain.flight.Flight;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterFlightUseCase implements RegisterFlight {

    private final FlightPersister flightPersister;

    @Override
    public void execute(RegisterFlightRequest request) {
        var registeredFlight = Flight.register(
                request.getId(),
                request.getDepartureAirport()
        );

        flightPersister.save(registeredFlight);
    }
}
