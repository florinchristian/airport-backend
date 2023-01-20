package dev.florinchristian.airportbackend.controller;

import dev.florinchristian.airportbackend.model.Flight;
import dev.florinchristian.airportbackend.model.Ticket;
import dev.florinchristian.airportbackend.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Flight> getFlights(
            @RequestParam(name = "fromAirport") @NonNull Integer fromAirport,
            @RequestParam(name = "toAirport") @NonNull Integer toAirport,
            @RequestParam(name = "departureDate") @NonNull String departureDate,
            @RequestParam(name = "numberOfSeats") @NonNull Integer numberOfSeats,

            @RequestParam(name = "returnDate", required = false) @NonNull String returnDate
    ) {
        return flightRepository
                .getAvailableFlights(
                        fromAirport,
                        toAirport,
                        departureDate,
                        numberOfSeats
                );
    }


    @RequestMapping(value = "/busySeats", method = RequestMethod.GET)
    public Map<Object, Object> getBusySeats(@RequestParam(name = "flightId") Integer flightId) {
        Map<Object, Object> result = new HashMap<>();

        Flight flight = flightRepository.findById(flightId).orElse(null);

        if (flight == null)
            return result;

        result.putIfAbsent("height", flight.getAirplane().getRowNumber());
        result.putIfAbsent("width", flight.getAirplane().getColumnNumber());

        Map<Object, Object> busySeats = new HashMap<>();

        List<Ticket> tickets = flight.getTickets();

        for (Ticket ticket: tickets)
            busySeats.putIfAbsent(ticket.getSelectedRow(), new HashMap<>() {
                {
                    putIfAbsent(ticket.getSelectedColumn(), true);
                }
            });

        result.putIfAbsent("busySeats", busySeats);

        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Flight insertFlight(@RequestBody @NonNull Flight flight) {
        return flightRepository.saveAndFlush(flight);
    }
}
