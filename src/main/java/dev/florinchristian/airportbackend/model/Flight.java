package dev.florinchristian.airportbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "airplane_id", nullable = false)
    private Airplane airplane;

    @ManyToOne
    @JoinColumn(name = "from_airport_id", nullable = false)
    private Airport fromAirport;

    @ManyToOne
    @JoinColumn(name = "to_airport_id", nullable = false)
    private Airport toAirport;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "price")
    private Integer price;

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//
//        if (!(obj instanceof Flight))
//            return false;
//
//        Flight flight = (Flight) obj;
//
//        LocalDate startDate1 = LocalDate.from(startTime);
//        LocalDate startDate2 = LocalDate.from(flight.getStartTime());
//    }

    public Flight(Airport fromAirport, Airport toAirport, LocalDateTime startTime) {
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.startTime = startTime;
    }
}
