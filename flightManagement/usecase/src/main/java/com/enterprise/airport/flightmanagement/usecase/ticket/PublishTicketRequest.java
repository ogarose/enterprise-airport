package com.enterprise.airport.flightmanagement.usecase.ticket;

import com.enterprise.airport.common.types.domain.common.Price;
import com.enterprise.airport.flightmanagement.domain.flight.FlightId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PublishTicketRequest {
    private FlightId flightId;
    private Price price;

    public PublishTicketRequest(Long flightId, BigDecimal price) {
        this.flightId = new FlightId(flightId);
        this.price = Price.from(price);
    }
}
