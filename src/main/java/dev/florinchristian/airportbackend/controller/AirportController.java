package dev.florinchristian.airportbackend.controller;

import dev.florinchristian.airportbackend.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import dev.florinchristian.airportbackend.model.Airport;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Set<Airport> getAirports(@RequestParam(required = false) String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty())
            return new HashSet<>(airportRepository.findAll());

        Set<Airport> result = new HashSet<>();

        result.addAll(airportRepository.findByNameContains(searchTerm));
        result.addAll(airportRepository.findByCountryContains(searchTerm));
        result.addAll(airportRepository.findByCountyContains(searchTerm));

        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Airport createAirport(@RequestBody @NonNull Airport airport) {
        return airportRepository.saveAndFlush(airport);
    }
}
