package dev.florinchristian.airportbackend.repository;

import dev.florinchristian.airportbackend.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<Airplane, Integer> {

}
