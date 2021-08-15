package com.enterprise.airport.flightmanagement.usecase.flight;

import com.enterprise.airport.common.types.domain.exception.DomainException;
import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftId;
import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftIsOccupiedByAnotherFlight;
import com.enterprise.airport.flightmanagement.domain.airoport.AirportAllowDepartureTime;
import com.enterprise.airport.flightmanagement.domain.flight.Flight;
import com.enterprise.airport.flightmanagement.domain.flight.FlightId;
import com.enterprise.airport.flightmanagement.domain.flight.FlightIdGenerator;
import com.enterprise.airport.flightmanagement.usecase.aircraft.AircraftExtractor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AnnounceFlightUseCase implements AnnounceFlight {

    private final AircraftIsOccupiedByAnotherFlight aircraftIsOccupiedByAnotherFlightRule;
    private final AirportAllowDepartureTime airportAllowDepartureTimeRule;
    private final FlightIdGenerator flightIdGenerator;
    private final FlightPersister flightPersister;
    private final AircraftExtractor aircraftExtractor;

    @Override
    public FlightId execute(AnnounceFlightRequest request) {
        var flightAircraft = aircraftExtractor.findById(request.getAircraftId())
                .orElseThrow(() -> IllegalAircraftIdException.ofAircraftId(request.getAircraftId()));

        var announcedFlight = Flight.announceFlight(
                aircraftIsOccupiedByAnotherFlightRule,
                airportAllowDepartureTimeRule,
                flightIdGenerator,
                request.getDepartureAirport(),
                request.getArrivalAirport(),
                request.getDepartureTime(),
                flightAircraft
        );

        flightPersister.save(announcedFlight);

        return announcedFlight.getId();
    }

    public static class IllegalAircraftIdException extends DomainException {
        public IllegalAircraftIdException(String message) {
            super(message);
        }

        public static IllegalAircraftIdException ofAircraftId(AircraftId aircraftId) {
            return new IllegalAircraftIdException(String.format("Wrong aircraft id: %s", aircraftId.getValue()));
        }
    }
}
