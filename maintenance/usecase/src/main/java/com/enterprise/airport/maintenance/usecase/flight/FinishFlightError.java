package com.enterprise.airport.maintenance.usecase.flight;

import com.enterprise.airport.common.types.error.UseCaseError;
import com.enterprise.airport.maintenance.domain.flight.FlightId;

public class FinishFlightError extends UseCaseError {
    static final class CanNotBeFinished extends FinishFlightError {
        public CanNotBeFinished() {
            this.message = "Flight can not be finished";
        }
    }

    static final class WrongFlightHoursError extends FinishFlightError {
        public WrongFlightHoursError() {
            this.message = "Wrong flight hours Error";
        }
    }

    static final class WrongAirportError extends FinishFlightError {

        public static WrongAirportError ofMessage(String message) {
            var instance = new WrongAirportError();
            instance.message = message;

            return instance;
        }
    }

    static final class IllegalFlightId extends FinishFlightError {

        public static IllegalFlightId ofFlightId(FlightId flightId) {
            var instance = new IllegalFlightId();
            instance.message = String.format("Wrong flight id: %s", flightId.getValue());

            return instance;
        }
    }
}
