package com.casestudy.amadeusflightservice.controller;

import com.casestudy.amadeusflightservice.request.AirportRequest;
import com.casestudy.amadeusflightservice.response.AirportResponse;
import com.casestudy.amadeusflightservice.response.DefaultMessageResponse;
import com.casestudy.amadeusflightservice.service.AirportService;
import com.casestudy.amadeusflightservice.util.SwaggerConstants;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;
    private static final String AUTHORIZATION = "Authorization";
    // Token is not used but provided because it is required to show in Swagger UI

    @GetMapping
    @Operation(summary = SwaggerConstants.GET_AIRPORTS_SUMMARY, description = SwaggerConstants.GET_AIRPORTS_DESCRIPTION)
    public ResponseEntity<AirportResponse> getAirports(@Valid @RequestHeader(AUTHORIZATION) @NotBlank String token) {
        return new ResponseEntity<>(airportService.getAirports(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = SwaggerConstants.ADD_AIRPORT_SUMMARY, description = SwaggerConstants.ADD_AIRPORT_DESCRIPTION)
    public ResponseEntity<DefaultMessageResponse> addAirport(@Valid @RequestHeader(AUTHORIZATION) @NotBlank String token, @Valid @RequestBody AirportRequest airportRequest) {
        return new ResponseEntity<>(airportService.addAirport(airportRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = SwaggerConstants.UPDATE_AIRPORT_SUMMARY, description = SwaggerConstants.UPDATE_AIRPORT_DESCRIPTION)
    public ResponseEntity<DefaultMessageResponse> updateAirport(@Valid @RequestHeader(AUTHORIZATION) @NotBlank String token, @PathVariable Long id, @Valid @RequestBody AirportRequest airportRequest) {
        return new ResponseEntity<>(airportService.updateAirport(id, airportRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = SwaggerConstants.DELETE_AIRPORT_SUMMARY, description = SwaggerConstants.DELETE_AIRPORT_DESCRIPTION)
    public ResponseEntity<DefaultMessageResponse> deleteAirport(@Valid @RequestHeader(AUTHORIZATION) @NotBlank String token, @PathVariable Long id) {
        return new ResponseEntity<>(airportService.deleteAirport(id), HttpStatus.OK);
    }

}
