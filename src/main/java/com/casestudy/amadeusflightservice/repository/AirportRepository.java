package com.casestudy.amadeusflightservice.repository;

import com.casestudy.amadeusflightservice.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    Optional<Airport> findByName(String name);

    List<Airport> findByCity(String city);
}
