package dev.florinchristian.airportbackend.controller;

import dev.florinchristian.airportbackend.model.Airport;
import dev.florinchristian.airportbackend.model.Flight;
import dev.florinchristian.airportbackend.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;


    // TODO implement flights get
    @RequestMapping(method = RequestMethod.GET)
    public List<Flight> getFlights(
            @RequestParam(name = "fromAirport") @NonNull Integer fromAirport,
            @RequestParam(name = "toAirport") @NonNull Integer toAirport,
            @RequestParam(name = "departureDate") @NonNull String departureDate,
            @RequestParam(name = "numberOfSeats") @NonNull Integer numberOfSeats,

            @RequestParam(name = "returnDate", required = false) @NonNull String returnDate
    ) {
        Flight wanted = new Flight(
                new Airport(fromAirport),
                new Airport(toAirport),
                LocalDateTime.parse(departureDate)
        );

        if (returnDate != null && !returnDate.isEmpty())
            wanted.setEndTime(LocalDateTime.parse(returnDate));

        return flightRepository.findAll(Example.of(wanted));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Flight insertFlight(@RequestBody @NonNull Flight flight) {
        return flightRepository.saveAndFlush(flight);
    }
}
