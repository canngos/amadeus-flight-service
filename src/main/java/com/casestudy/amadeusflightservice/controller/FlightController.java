package com.casestudy.amadeusflightservice.controller;

import com.casestudy.amadeusflightservice.request.FlightRequest;
import com.casestudy.amadeusflightservice.response.DefaultMessageResponse;
import com.casestudy.amadeusflightservice.response.FlightResponse;
import com.casestudy.amadeusflightservice.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<FlightResponse> getFlights() {
        return new ResponseEntity<>(flightService.getFlights(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DefaultMessageResponse> addFlight(@Valid @RequestBody FlightRequest flightRequest) {
        return new ResponseEntity<>(flightService.addFlight(flightRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultMessageResponse> updateFlight(@PathVariable Long id, @Valid @RequestBody FlightRequest flightRequest) {
        return new ResponseEntity<>(flightService.updateFlight(id, flightRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultMessageResponse> deleteFlight(@PathVariable Long id) {
        return new ResponseEntity<>(flightService.deleteFlight(id), HttpStatus.OK);
    }
}
