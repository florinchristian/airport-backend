package dev.florinchristian.airportbackend.repository;

import dev.florinchristian.airportbackend.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirplaneRepository extends JpaRepository<Airplane, Integer> {

    @Query(value = """
                select airplanes.id, airplanes.name, airplanes.row_number, airplanes.column_number
                from airplanes
                inner join flights
                on airplanes.id != flights.airplane_id or ( airplanes.id = flights.airplane_id and (
                	?1 not between flights.start_time and flights.end_time
                    and
                	?2 not BETWEEN flights.start_time and flights.end_time
                ));
            """, nativeQuery = true)
    List<Airplane> findAvailableAirplanesBetween(String start, String end);
}
