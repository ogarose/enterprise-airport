package com.enterprise.airport.common.types.exception;

public class DomainException extends RuntimeException {

    @Override
    public String toString() {
        return String.format("Domain Exception. Message: %s .", this.getMessage());
    }
}
