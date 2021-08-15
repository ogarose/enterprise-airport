package com.enterprise.airport.maintenance.domain.flight;

import com.enterprise.airport.common.types.domain.error.BusinessError;

public class FlightError extends BusinessError {

    public static final class CanNotBeFinished extends FlightError {
        public CanNotBeFinished() {
            this.message = "Wrong flight status";
        }
    }
}
