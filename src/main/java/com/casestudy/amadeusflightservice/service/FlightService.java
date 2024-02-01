package com.casestudy.amadeusflightservice.service;

import com.casestudy.amadeusflightservice.request.FlightRequest;
import com.casestudy.amadeusflightservice.response.DefaultMessageResponse;
import com.casestudy.amadeusflightservice.response.FlightResponse;

public interface FlightService {
    FlightResponse getFlights();

    DefaultMessageResponse addFlight(FlightRequest flightRequest);

    DefaultMessageResponse updateFlight(Long id, FlightRequest flightRequest);

    DefaultMessageResponse deleteFlight(Long id);
}

