package com.casestudy.amadeusflightservice.controller;

import com.casestudy.amadeusflightservice.response.FlightResponse;
import com.casestudy.amadeusflightservice.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/search/flights")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/default")
    public ResponseEntity<FlightResponse> searchFlights(@RequestParam("departureAirport") String departureAirport,
                                                        @RequestParam("arrivalAirport") String arrivalAirport,
                                                        @RequestParam("departureDate") String departureDate,
                                                        @RequestParam(value = "returnDate", required = false) String returnDate) {

        if (returnDate.isBlank()) {
            return new ResponseEntity<>(searchService.searchFlights(departureAirport, arrivalAirport, departureDate), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(searchService.searchFlights(departureAirport, arrivalAirport, departureDate, returnDate), HttpStatus.OK);
        }

    }

    @GetMapping("/city")
    public ResponseEntity<FlightResponse> searchFlightsByCity(@RequestParam("departureCity") String departureCity,
                                                        @RequestParam("arrivalCity") String arrivalCity,
                                                        @RequestParam("departureDate") String departureDate,
                                                        @RequestParam(value = "returnDate", required = false) String returnDate) {

        if (returnDate.isBlank()) {
            return new ResponseEntity<>(searchService.searchFlightsByCity(departureCity, arrivalCity, departureDate), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(searchService.searchFlightsByCity(departureCity, arrivalCity, departureDate, returnDate), HttpStatus.OK);
        }
    }

}
