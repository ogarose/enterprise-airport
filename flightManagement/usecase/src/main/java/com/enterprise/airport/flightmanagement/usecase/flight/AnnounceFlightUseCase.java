package com.enterprise.airport.flightmanagement.usecase.flight;

import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftIsOccupiedByAnotherFlight;
import com.enterprise.airport.flightmanagement.domain.airoport.AirportAllowDepartureTime;
import com.enterprise.airport.flightmanagement.domain.flight.Flight;
import com.enterprise.airport.flightmanagement.domain.flight.FlightId;
import com.enterprise.airport.flightmanagement.domain.flight.FlightIdGenerator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AnnounceFlightUseCase implements AnnounceFlight {

    private final AircraftIsOccupiedByAnotherFlight aircraftIsOccupiedByAnotherFlightRule;
    private final AirportAllowDepartureTime airportAllowDepartureTimeRule;
    private final FlightIdGenerator flightIdGenerator;
    private final FlightPersister flightPersister;

    @Override
    public FlightId execute(AnnounceFlightRequest request) {

        var announcedFlight = Flight.announceFlight(
                aircraftIsOccupiedByAnotherFlightRule,
                airportAllowDepartureTimeRule,
                flightIdGenerator,
                request.getDepartureAirport(),
                request.getArrivalAirport(),
                request.getDepartureTime(),
                request.getAircraftId()
        );

        flightPersister.save(announcedFlight);

        return announcedFlight.getId();
    }
}
