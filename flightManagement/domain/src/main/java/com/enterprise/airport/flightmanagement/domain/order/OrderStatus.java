package com.enterprise.airport.flightmanagement.domain.order;

public enum OrderStatus {
    CREATED {
        @Override
        public boolean canBeChangeTo(OrderStatus status) {
            return true;
        }
    },
    PAID {
        @Override
        public boolean canBeChangeTo(OrderStatus status) {
            return status.equals(OrderStatus.CREATED);
        }
    };

    public abstract boolean canBeChangeTo(OrderStatus status);
}
