package com.enterprise.airport.maintenance.domain.flight;

public enum FlightStatus {
    REGISTER {
        @Override
        public boolean canBeChangeTo(FlightStatus status) {
            return true;
        }
    },
    FINISHED {
        @Override
        public boolean canBeChangeTo(FlightStatus status) {
            return status.equals(FlightStatus.REGISTER);
        }
    };

    public abstract boolean canBeChangeTo(FlightStatus status);
}
