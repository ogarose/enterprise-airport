package com.enterprise.airport.flightmanagement.domain.flight;

import com.enterprise.airport.common.types.base.AggregateRoot;
import com.enterprise.airport.common.types.base.Version;
import com.enterprise.airport.common.types.common.Airport;
import com.enterprise.airport.common.types.exception.DomainException;
import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftId;
import com.enterprise.airport.flightmanagement.domain.aircraft.AircraftIsOccupiedByAnotherFlight;
import com.enterprise.airport.flightmanagement.domain.airoport.AirportAllowDepartureTime;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Flight extends AggregateRoot<FlightId> {
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime departureTime;
    private AircraftId aircraftId;

    protected Flight(
            FlightId id,
            Version first,
            Airport departureAirport,
            Airport arrivalAirport,
            LocalDateTime departureTime,
            AircraftId aircraftId
    ) {
        super(id, first);
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.aircraftId = aircraftId;
    }

    public static Flight announceFlight(
            AircraftIsOccupiedByAnotherFlight aircraftIsOccupied,
            AirportAllowDepartureTime departureTimeAllowed,
            FlightIdGenerator flightIdGenerator,
            Airport departureAirport,
            Airport arrivalAirport,
            LocalDateTime departureTime,
            AircraftId aircraftId
    ) {
        if (aircraftIsOccupied.check(aircraftId)) {
            throw new AircraftIsOccupiedByAnotherFlightException();
        }

        if (!departureTimeAllowed.check(departureAirport, departureTime)) {
            throw new DepartureTimeIsNotAllowedException();
        }

        var flight = new Flight(
                flightIdGenerator.generate(),
                Version.first(),
                departureAirport,
                arrivalAirport,
                departureTime,
                aircraftId
        );

        flight.addEvent(new FlightEvent.FlightAnnounced(flight.getId()));

        return flight;
    }

    public static class AircraftIsOccupiedByAnotherFlightException extends DomainException {
    }

    public static class DepartureTimeIsNotAllowedException extends DomainException {
    }
}
