package com.casestudy.amadeusflightservice.service;

import com.casestudy.amadeusflightservice.request.AirportRequest;
import com.casestudy.amadeusflightservice.response.AirportResponse;
import com.casestudy.amadeusflightservice.response.DefaultMessageResponse;

public interface AirportService {
    AirportResponse getAirports();

    DefaultMessageResponse addAirport(AirportRequest airportRequest);

    DefaultMessageResponse updateAirport(Long id, AirportRequest airportRequest);

    DefaultMessageResponse deleteAirport(Long id);
}
