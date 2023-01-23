package dev.florinchristian.airportbackend.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class TicketRequest {
    private Integer userId;
    private Integer flightId;
    private Map<Integer, Map<Integer, Boolean>> selectedSeats;
}
