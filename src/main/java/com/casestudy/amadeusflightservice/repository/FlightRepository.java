package com.casestudy.amadeusflightservice.repository;

import com.casestudy.amadeusflightservice.entity.Airport;
import com.casestudy.amadeusflightservice.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDepartureAirportAndArrivalAirport(Airport departureAirport, Airport arrivalAirport);

    Optional<Flight> findByFlightNumberAndDepartureTime(String flightNumber, LocalDateTime departureTime);
}
