package dev.florinchristian.airportbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airplanes")
@Getter
@Setter
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "column_number")
    private Integer columnNumber;

    @Column(name = "row_number")
    private Integer rowNumber;

    @JsonIgnore
    @OneToMany(
            mappedBy = "airplane",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Flight> flights = new ArrayList<>();
}
