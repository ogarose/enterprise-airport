package com.enterprise.airport.common.types.exception;

public class DomainException extends RuntimeException {

    public DomainException() {
        super();
    }

    public DomainException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return String.format("Domain Exception. Message: %s .", this.getMessage());
    }
}
