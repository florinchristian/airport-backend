package dev.florinchristian.airportbackend.repository;

import dev.florinchristian.airportbackend.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

}
