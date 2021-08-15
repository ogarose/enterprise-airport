package com.enterprise.airport.maintenance.usecase.flight;

import com.enterprise.airport.common.types.domain.common.Airport;
import com.enterprise.airport.maintenance.domain.flight.FlightHours;
import com.enterprise.airport.maintenance.domain.flight.FlightId;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import lombok.Data;

@Data
public class FinishFlightRequest {
    private FlightId flightId;
    private Airport arrivedAirport;
    private FlightHours flightHours;

    public FinishFlightRequest(
            FlightId flightId,
            Airport arrivedAirport,
            FlightHours flightHours
    ) {
        this.flightId = flightId;
        this.arrivedAirport = arrivedAirport;
        this.flightHours = flightHours;
    }

    public static Either<Seq<FinishFlightError>, FinishFlightRequest> from(
            Long flightId,
            String arrivedAirport,
            Long flightHours
    ) {
        return Validation.combine(
                Validation.valid(new FlightId(flightId)),
                Validation.fromEither(
                        Try.of(() -> Airport.valueOf(arrivedAirport))
                                .toEither()
                                .mapLeft(exc -> FinishFlightError.WrongAirportError.ofMessage(exc.getMessage()))
                ),
                Validation.fromEither(
                        FlightHours.from(flightHours)
                                .mapLeft(error -> new FinishFlightError.WrongFlightHoursError())
                )
        )
                .ap(FinishFlightRequest::new)
                .toEither();
    }
}
