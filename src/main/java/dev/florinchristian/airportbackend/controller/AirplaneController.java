package dev.florinchristian.airportbackend.controller;

import dev.florinchristian.airportbackend.model.Airplane;
import dev.florinchristian.airportbackend.repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/airplanes")
public class AirplaneController {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @RequestMapping(value = "/availableBetween", method = RequestMethod.GET)
    public List<Airplane> getAvailableAirplanes(
            @RequestParam(name = "startDate") @NonNull String startDate,
            @RequestParam(name = "endDate") @NonNull String endDate
    ) {
        return airplaneRepository.findAvailableAirplanesBetween(startDate, endDate);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Airplane> getAirplanes() {
        return airplaneRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Airplane insertAirplane(@RequestBody @NonNull Airplane airplane) {
        return airplaneRepository.saveAndFlush(airplane);
    }
}
