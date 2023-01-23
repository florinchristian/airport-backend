package dev.florinchristian.airportbackend.controller;

import dev.florinchristian.airportbackend.model.Flight;
import dev.florinchristian.airportbackend.model.Ticket;
import dev.florinchristian.airportbackend.model.TicketRequest;
import dev.florinchristian.airportbackend.model.User;
import dev.florinchristian.airportbackend.repository.FlightRepository;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Flight getById(@PathVariable(name = "id") Integer id) {
        return flightRepository.findById(id).orElse(null);
    }


    @RequestMapping(value = "/byDay", method = RequestMethod.GET)
    public List<Flight> getByDate(@RequestParam(name = "day") String day) {
        return flightRepository.getFlightsByDay(day);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Flight> getFlights(
            @RequestParam(name = "fromAirport") @NonNull Integer fromAirport,
            @RequestParam(name = "toAirport") @NonNull Integer toAirport,
            @RequestParam(name = "departureDate") @NonNull String departureDate,
            @RequestParam(name = "numberOfSeats") @NonNull Integer numberOfSeats,

            @RequestParam(name = "priceFrom", required = false, defaultValue = "0") @NonNull Integer priceFrom,
            @RequestParam(name = "priceTo", required = false, defaultValue = "9999") @NonNull Integer priceTo,

            @RequestParam(name = "returnDate", required = false) @NonNull String returnDate
    ) {
        return flightRepository
                .getAvailableFlights(
                        fromAirport,
                        toAirport,
                        departureDate,
                        numberOfSeats,
                        priceFrom,
                        priceTo
                );
    }


    @RequestMapping(value = "/busySeats", method = RequestMethod.GET)
    public Map<Object, Object> getBusySeats(@RequestParam(name = "flightId") Integer flightId) {
        Map<Object, Object> result = new HashMap<>();

        Flight flight = flightRepository.findById(flightId).orElse(null);

        if (flight == null)
            return result;

        result.putIfAbsent("flightId", flightId);
        result.putIfAbsent("height", flight.getAirplane().getRowNumber());
        result.putIfAbsent("width", flight.getAirplane().getColumnNumber());

        Map<Object, List<Integer>> busySeats = new HashMap<>();

        List<Ticket> tickets = flight.getTickets();

        for (Ticket ticket: tickets) {
            busySeats.putIfAbsent(ticket.getSelectedRow(), new ArrayList<>());
            busySeats.get(ticket.getSelectedRow())
                    .add(ticket.getSelectedColumn());
        }

        result.putIfAbsent("busySeats", busySeats);

        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Flight insertFlight(@RequestBody @NonNull TicketRequest ticketRequest) {
        Flight flight = flightRepository.findById(ticketRequest.getFlightId()).orElse(null);

        if (flight == null)
            return null;

        Map<Integer, Map<Integer, Boolean>> selectedSeats = ticketRequest.getSelectedSeats();

        List<Ticket> tickets = flight.getTickets();

        int count = 0;

        for (Integer row: selectedSeats.keySet())
            for (Integer column: selectedSeats.get(row).keySet())
                if (selectedSeats.get(row).get(column)) {
                    tickets.add(new Ticket(
                            new User(ticketRequest.getUserId()),
                            new Flight(ticketRequest.getFlightId()),
                            row,
                            column
                    ));

                    count++;
                }

        flight.setSeatsLeft(flight.getSeatsLeft() - count);

        return flightRepository.saveAndFlush(flight);
    }
}
