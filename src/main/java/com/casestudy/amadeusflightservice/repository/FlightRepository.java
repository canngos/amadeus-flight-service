package com.casestudy.amadeusflightservice.repository;

import com.casestudy.amadeusflightservice.entity.Airport;
import com.casestudy.amadeusflightservice.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByDepartureAirportAndArrivalAirport(Airport departureAirport, Airport arrivalAirport);
}
