package dev.florinchristian.airportbackend.controller;


import dev.florinchristian.airportbackend.model.Flight;
import dev.florinchristian.airportbackend.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;


    @RequestMapping(method = RequestMethod.GET)
    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }

    public Flight insertFlight(@RequestBody @NonNull Flight flight) {
        return flightRepository.saveAndFlush(flight);
    }
}
