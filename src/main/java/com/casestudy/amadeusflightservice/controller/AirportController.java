package com.casestudy.amadeusflightservice.controller;

import com.casestudy.amadeusflightservice.request.AirportRequest;
import com.casestudy.amadeusflightservice.response.AirportResponse;
import com.casestudy.amadeusflightservice.response.DefaultMessageResponse;
import com.casestudy.amadeusflightservice.service.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public ResponseEntity<AirportResponse> getAirports() {
        return new ResponseEntity<>(airportService.getAirports(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DefaultMessageResponse> addAirport(@Valid @RequestBody AirportRequest airportRequest) {
        return new ResponseEntity<>(airportService.addAirport(airportRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultMessageResponse> updateAirport(@PathVariable Long id, @Valid @RequestBody AirportRequest airportRequest) {
        return new ResponseEntity<>(airportService.updateAirport(id, airportRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultMessageResponse> deleteAirport(@PathVariable Long id) {
        return new ResponseEntity<>(airportService.deleteAirport(id), HttpStatus.OK);
    }

}
