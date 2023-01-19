package dev.florinchristian.airportbackend.repository;

import dev.florinchristian.airportbackend.model.Airport;
import dev.florinchristian.airportbackend.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    //List<Flight> findAllByStartTimeGreaterThanEqualAndEndTimeLessThan(LocalDateTime left, LocalDateTime right);
    // I did a @Query after all because ^ that's quite big ^

    List<Flight> getAvailableFlights(Integer fromAirport, Integer toAirport);
}
