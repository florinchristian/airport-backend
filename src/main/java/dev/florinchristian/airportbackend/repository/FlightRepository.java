package dev.florinchristian.airportbackend.repository;

import dev.florinchristian.airportbackend.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    //List<Flight> findAllByStartTimeGreaterThanEqualAndEndTimeLessThan(LocalDateTime left, LocalDateTime right);
    // I did a @Query after all because ^ that's quite big ^

    @Query(value = """
            select * from flights
                    where
                    	seats_left - ?4 >= 0
                    and
                        from_airport_id = ?1
                    and
                        to_airport_id = ?2
                    and
                        start_time >= ?3
                    and
                        start_time < date_add(?3, interval 1 day);
            """, nativeQuery = true)
    List<Flight> getAvailableFlights(Integer fromAirport, Integer toAirport, String date, Integer numberOfSeats);
}
