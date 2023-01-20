package dev.florinchristian.airportbackend.controller;

import dev.florinchristian.airportbackend.model.Ticket;
import dev.florinchristian.airportbackend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
