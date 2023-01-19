package dev.florinchristian.airportbackend.repository;

import dev.florinchristian.airportbackend.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public abstract class FlightServiceImpl implements FlightService {
    @Autowired
    protected FlightRepository flightRepository;

    public List<Flight> getAvailableFlights(
            Integer fromAirport,
            Integer toAirport,
            String departureDate,
            Integer numberOfSeats,

            String returnDate
    ) {


        return new ArrayList<>();
    }

    public Flight saveAndFlush(Flight flight) {
        return flightRepository.saveAndFlush(flight);
    }
}
