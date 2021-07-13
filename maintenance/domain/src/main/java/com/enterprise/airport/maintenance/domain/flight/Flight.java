package com.enterprise.airport.maintenance.domain.flight;

import com.enterprise.airport.common.types.base.AggregateRoot;
import com.enterprise.airport.common.types.base.Version;
import com.enterprise.airport.common.types.common.Airport;
import com.enterprise.airport.common.types.exception.DomainException;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Flight extends AggregateRoot<FlightId> {
    private Airport departureAirport;
    private Airport arrivedAirport;
    private FlightHours flightHours;
    private FlightStatus status;

    protected Flight(
            FlightId id,
            Version first,
            Airport departureAirport,
            Airport arrivedAirport,
            FlightHours flightHours,
            FlightStatus status
    ) {
        super(id, first);
        this.departureAirport = departureAirport;
        this.arrivedAirport = arrivedAirport;
        this.flightHours = flightHours == null ? new FlightHours(0L) : flightHours;
        this.status = status == null ? FlightStatus.REGISTER : status;
    }

    public void finish(
            @NonNull Airport arrivedAirport,
            @NonNull FlightHours flightHours
    ) {
        if (!status.canBeChangeTo(FlightStatus.FINISHED)) {
            throw new CanNotBeFinishedException();
        }

        this.arrivedAirport = arrivedAirport;
        this.flightHours = flightHours;
        this.status = FlightStatus.FINISHED;

        this.addEvent(new FlightEvent.FlightArrived(this.getId()));
    }

    public static Flight register(
            FlightId id,
            Airport departureAirport
    ) {
        var registeredFlight = new Flight(
                id,
                Version.first(),
                departureAirport,
                null,
                new FlightHours(0L),
                FlightStatus.REGISTER
        );

        registeredFlight.addEvent(new FlightEvent.FlightRegistered(registeredFlight.getId()));

        return registeredFlight;
    }

    public static class CanNotBeFinishedException extends DomainException{}
}