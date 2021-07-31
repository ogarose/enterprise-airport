package com.enterprise.airport.maintenance.usecase.flight;


import com.enterprise.airport.common.types.error.GeneralUseCaseError;
import com.enterprise.airport.maintenance.domain.flight.FlightError;
import io.vavr.API;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.Predicates.instanceOf;

@AllArgsConstructor
public class FinishFlightUseCase implements FinishFlight {

    private final FlightExtractor flightExtractor;
    private final FlightPersister flightPersister;


    @Override
    public Either<? super FinishFlightError, Void> execute(FinishFlightRequest request) {
        var flightOption = flightExtractor.getById(request.getFlightId());

        if (flightOption.isEmpty()) {
            return Either.left(FinishFlightError.IllegalFlightId.ofFlightId(request.getFlightId()));
        }

        var flight = flightOption.get();

        return flight.finish(request.getArrivedAirport(), request.getFlightHours())
                .mapLeft(error -> API.Match(error)
                        .of(
                                Case(
                                        $(instanceOf(FlightError.CanNotBeFinished.class)),
                                        businessError -> new FinishFlightError.CanNotBeFinished()
                                ),
                                Case($(), GeneralUseCaseError::new)
                        )
                )
                .map(
                        result -> {
                            flightPersister.save(flight);
                            return result;
                        }
                );
    }
}
