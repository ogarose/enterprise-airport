package com.enterprise.airport.integration.airport;

import com.enterprise.airport.common.types.common.Airport;
import com.enterprise.airport.flightmanagement.domain.airoport.AirportAllowDepartureTime;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class AirportAllowDepartureTimeImpl implements AirportAllowDepartureTime {

    @SuppressWarnings("PMD.UnusedPrivateField")
    private static final String URL_AIRPORT_ALLOW_DEPARTURE = "api_url";

    @Override
    public boolean check(Airport airport, LocalDateTime departureTime) {
        //CHECKSTYLE:OFF
        // Emulate API call
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response = restTemplate
//                .exchange(URL_AIRPORT_ALLOW_DEPARTURE, HttpMethod.GET, null, String.class);
//        return Boolean.parseBoolean(response.getBody(), true);
        //CHECKSTYLE:ON

        return ThreadLocalRandom.current().nextBoolean();
    }
}
