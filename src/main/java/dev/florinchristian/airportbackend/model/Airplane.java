package dev.florinchristian.airportbackend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
