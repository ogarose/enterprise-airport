package com.enterprise.airport.maintenance.usecase.flight;


import com.enterprise.airport.common.types.exception.DomainException;
import com.enterprise.airport.maintenance.domain.flight.FlightId;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FinishFlightUseCase implements FinishFlight {

    private final FlightExtractor flightExtractor;
    private final FlightPersister flightPersister;


    @Override
    public void execute(FinishFlightRequest request) {
        var flight = flightExtractor.getById(request.getFlightId())
                .orElseThrow(() -> IllegalFlightIdException.ofFlightId(request.getFlightId()));

        flight.finish(request.getArrivedAirport(), request.getFlightHours());

        flightPersister.save(flight);
    }

    public static class IllegalFlightIdException extends DomainException {
        public IllegalFlightIdException(String message) {
            super(message);
        }

        public static IllegalFlightIdException ofFlightId(FlightId flightId) {
            return new IllegalFlightIdException(String.format("Wrong flight id: %s", flightId.getValue()));
        }
    }
}
