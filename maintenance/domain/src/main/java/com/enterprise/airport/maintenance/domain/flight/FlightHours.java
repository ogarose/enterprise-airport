package com.enterprise.airport.maintenance.domain.flight;

import com.enterprise.airport.common.types.base.ValueObject;
import com.enterprise.airport.common.types.error.BusinessError;
import io.vavr.control.Either;
import lombok.Value;

@Value
public class FlightHours implements ValueObject {
    Long value;

    private FlightHours(Long value) {
        this.value = value;
    }

    public static Either<BusinessError, FlightHours> from(Long value) {
        if (validate(value)) {
            return Either.right(new FlightHours(value));
        } else {
            return Either.left(WrongFlightHoursError.from(value));
        }
    }

    public static FlightHours zero() {
        return new FlightHours(0L);
    }

    private static boolean validate(Long value) {
        return !(value == null || value < 0);
    }

    public static class WrongFlightHoursError extends BusinessError {

        public static WrongFlightHoursError from(Long value) {
            var instance = new WrongFlightHoursError();
            instance.message = String.format("Income value: %d", value);

            return instance;
        }
    }
}
