package dev.florinchristian.airportbackend.controller;

import dev.florinchristian.airportbackend.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import dev.florinchristian.airportbackend.model.Airport;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Airport> getAirports() {
        return airportRepository.findAll();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Airport> searchForAirports(@RequestParam @NonNull String searchTerm) {
        List<Airport> result = new ArrayList<>();

        result.addAll(airportRepository.findByNameContains(searchTerm));
        result.addAll(airportRepository.findByCountyContains(searchTerm));
        result.addAll(airportRepository.findByCountryContains(searchTerm));

        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Airport createAirport(@RequestBody @NonNull Airport airport) {
        return airportRepository.saveAndFlush(airport);
    }
}
