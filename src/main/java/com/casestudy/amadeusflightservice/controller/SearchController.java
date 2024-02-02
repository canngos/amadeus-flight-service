package com.casestudy.amadeusflightservice.controller;

import com.casestudy.amadeusflightservice.response.FlightResponse;
import com.casestudy.amadeusflightservice.service.SearchService;
import com.casestudy.amadeusflightservice.util.SwaggerConstants;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/search/flights")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;
    private static final String AUTHORIZATION = "Authorization";
    // Token is not used but provided because it is required to show in Swagger UI

    @GetMapping("/default")
    @Operation(summary = SwaggerConstants.SEARCH_FLIGHTS_SUMMARY, description = SwaggerConstants.SEARCH_FLIGHTS_DESCRIPTION)
    public ResponseEntity<FlightResponse> searchFlights(@Valid @RequestHeader(AUTHORIZATION) @NotBlank String token,
                                                        @RequestParam("departureAirport") String departureAirport,
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
    @Operation(summary = SwaggerConstants.SEARCH_FLIGHTS_BY_CITY_SUMMARY, description = SwaggerConstants.SEARCH_FLIGHTS_BY_CITY_DESCRIPTION)
    public ResponseEntity<FlightResponse> searchFlightsByCity(@Valid @RequestHeader(AUTHORIZATION) @NotBlank String token,
                                                              @RequestParam("departureCity") String departureCity,
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
