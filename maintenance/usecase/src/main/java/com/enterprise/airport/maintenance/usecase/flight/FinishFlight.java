package com.enterprise.airport.maintenance.usecase.flight;

import io.vavr.control.Either;

public interface FinishFlight {
    Either<? super FinishFlightError, Void> execute(FinishFlightRequest request);
}
