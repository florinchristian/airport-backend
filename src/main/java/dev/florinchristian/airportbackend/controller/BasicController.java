package dev.florinchristian.airportbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BasicController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "Airport API is running.";
    }
}
