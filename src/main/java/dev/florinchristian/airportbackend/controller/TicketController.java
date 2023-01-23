package dev.florinchristian.airportbackend.controller;

import dev.florinchristian.airportbackend.model.Flight;
import dev.florinchristian.airportbackend.model.Ticket;
import dev.florinchristian.airportbackend.model.TicketRequest;
import dev.florinchristian.airportbackend.model.User;
import dev.florinchristian.airportbackend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<Ticket> getUserTickets(@RequestParam(name = "userId", required = false) Integer userId) {
        return ticketRepository.findByUserId(userId);
    }

    @RequestMapping(value = "/flight", method = RequestMethod.GET)
    public List<Ticket> getFlightTickets(@RequestParam(name = "flightId", required = false) Integer flightId) {
        return ticketRepository.findByFlightId(flightId);
    }
}
