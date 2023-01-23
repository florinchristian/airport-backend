package dev.florinchristian.airportbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    @JsonIgnore
    private Flight flight;

    @Column(name = "selected_row")
    private Integer selectedRow;

    @Column(name = "selected_column")
    private Integer selectedColumn;

    public Ticket(User user, Flight flight, Integer selectedRow, Integer selectedColumn) {
        this.user = user;
        this.flight = flight;
        this.selectedRow = selectedRow;
        this.selectedColumn = selectedColumn;
    }
}
