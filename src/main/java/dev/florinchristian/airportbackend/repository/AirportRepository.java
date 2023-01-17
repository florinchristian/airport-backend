package dev.florinchristian.airportbackend.repository;

import dev.florinchristian.airportbackend.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {
    List<Airport> findByCountryContains(String country);
    List<Airport> findByCountyContains(String county);
    List<Airport> findByNameContains(String name);
}
