package com.enterprise.airport.common.types.base;

public class AggregateRoot<T> extends DomainEntity<T> {
    public AggregateRoot(T id, Version first) {
        super(id, first);
    }
}
