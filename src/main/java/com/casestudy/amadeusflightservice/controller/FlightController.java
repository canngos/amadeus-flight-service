package com.casestudy.amadeusflightservice.controller;

import com.casestudy.amadeusflightservice.request.FlightRequest;
import com.casestudy.amadeusflightservice.response.DefaultMessageResponse;
import com.casestudy.amadeusflightservice.response.FlightResponse;
import com.casestudy.amadeusflightservice.service.FlightService;
import com.casestudy.amadeusflightservice.util.SwaggerConstants;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;
    private static final String AUTHORIZATION = "Authorization";
    // Token is not used but provided because it is required to show in Swagger UI

    @GetMapping
    @Operation(summary = SwaggerConstants.GET_FLIGHTS_SUMMARY, description = SwaggerConstants.GET_FLIGHTS_DESCRIPTION)
    public ResponseEntity<FlightResponse> getFlights(@Valid @RequestHeader(AUTHORIZATION) @NotBlank String token) {
        return new ResponseEntity<>(flightService.getFlights(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = SwaggerConstants.ADD_FLIGHT_SUMMARY, description = SwaggerConstants.ADD_FLIGHT_DESCRIPTION)
    public ResponseEntity<DefaultMessageResponse> addFlight(@Valid @RequestHeader(AUTHORIZATION) @NotBlank String token, @Valid @RequestBody FlightRequest flightRequest) {
        return new ResponseEntity<>(flightService.addFlight(flightRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = SwaggerConstants.UPDATE_FLIGHT_SUMMARY, description = SwaggerConstants.UPDATE_FLIGHT_DESCRIPTION)
    public ResponseEntity<DefaultMessageResponse> updateFlight(@Valid @RequestHeader(AUTHORIZATION) @NotBlank String token, @PathVariable Long id, @Valid @RequestBody FlightRequest flightRequest) {
        return new ResponseEntity<>(flightService.updateFlight(id, flightRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = SwaggerConstants.DELETE_FLIGHT_SUMMARY, description = SwaggerConstants.DELETE_FLIGHT_DESCRIPTION)
    public ResponseEntity<DefaultMessageResponse> deleteFlight(@Valid @RequestHeader(AUTHORIZATION) @NotBlank String token, @PathVariable Long id) {
        return new ResponseEntity<>(flightService.deleteFlight(id), HttpStatus.OK);
    }
}
