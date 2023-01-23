package dev.florinchristian.airportbackend.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Seat {
    private Integer row;
    private Integer column;
}
