package com.casestudy.amadeusflightservice.service;

import com.casestudy.amadeusflightservice.response.FlightResponse;

public interface SearchService {
    FlightResponse searchFlights(String departureAirport, String arrivalAirport, String departureDate);
    FlightResponse searchFlights(String departureAirport, String arrivalAirport, String departureDate, String returnDate);

    FlightResponse searchFlightsByCity(String departureCity, String arrivalCity, String departureDate);
    FlightResponse searchFlightsByCity(String departureCity, String arrivalCity, String departureDate, String returnDate);
}
