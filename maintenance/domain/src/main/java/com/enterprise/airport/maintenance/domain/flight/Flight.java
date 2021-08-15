package com.enterprise.airport.maintenance.domain.flight;

import com.enterprise.airport.common.types.domain.base.AggregateRoot;
import com.enterprise.airport.common.types.domain.base.Version;
import com.enterprise.airport.common.types.domain.common.Airport;
import io.vavr.control.Either;
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
        this.flightHours = flightHours == null ? FlightHours.zero() : flightHours;
        this.status = status == null ? FlightStatus.REGISTER : status;
    }

    public Either<FlightError, Void> finish(
            @NonNull Airport arrivedAirport,
            @NonNull FlightHours flightHours
    ) {
        if (!status.canBeChangeTo(FlightStatus.FINISHED)) {
            return Either.left(new FlightError.CanNotBeFinished());
        }

        this.arrivedAirport = arrivedAirport;
        this.flightHours = flightHours;
        this.status = FlightStatus.FINISHED;

        this.addEvent(new FlightEvent.FlightArrived(this.getId()));

        return Either.right(null);
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
                FlightHours.zero(),
                FlightStatus.REGISTER
        );

        registeredFlight.addEvent(new FlightEvent.FlightRegistered(registeredFlight.getId()));

        return registeredFlight;
    }
}
