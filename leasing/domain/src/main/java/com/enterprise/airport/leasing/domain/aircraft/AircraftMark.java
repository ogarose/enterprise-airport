package com.enterprise.airport.leasing.domain.aircraft;

public enum AircraftMark {
    BOEING_747("747"),
    BOEING_777("777"),
    AIRBUS_A220("A220"),
    AIRBUS_A320("A220");

    private final String code;

    AircraftMark(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
