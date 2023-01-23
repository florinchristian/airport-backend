package dev.florinchristian.airportbackend.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TicketRequest {
    private Integer userId;
    private Integer flightId;
    private List<Seat> selectedSeats;
}
