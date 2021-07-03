package com.enterprise.airport.common.types.common;


public enum AircraftModel {
    BOEING_747("747"),
    BOEING_777("777"),
    AIRBUS_A220("A220"),
    AIRBUS_A320("A320");

    private final String code;

    AircraftModel(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
