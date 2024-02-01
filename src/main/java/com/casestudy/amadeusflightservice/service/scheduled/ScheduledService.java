package com.casestudy.amadeusflightservice.service.scheduled;

import com.casestudy.amadeusflightservice.dto.FeignDto;
import com.casestudy.amadeusflightservice.entity.Airport;
import com.casestudy.amadeusflightservice.entity.Flight;
import com.casestudy.amadeusflightservice.repository.AirportRepository;
import com.casestudy.amadeusflightservice.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

/*
    * This class is used to pull data from mockapi.io and save it to the database.
    * It is scheduled to run every day at 12 AM.
    * It checks if the flight already exists in the database, if not, it saves it.
    * It also checks if the airports already exist in the database, if not, it saves a new airport.
    * My mock api url is: https://65ba0cd5b4d53c066551eb64.mockapi.io/mockapi/v1/flights
    * Currently, there are 3 flights in the mock api.
 */

public class ScheduledService {

    private final FeignDataPullService feignDataPullService;
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    @Scheduled(cron = "0 0 12 * * *") // every day at 12 AM
    public void pullData() {
        log.info("Pulling data from mockapi");
        List<FeignDto> flights = feignDataPullService.getFlights();
        LocalDateTime now = LocalDateTime.now();

        flights.forEach(feignDto -> {
            // if the departure time is before today's date, then skip
            if (feignDto.getDepartureTime().isBefore(now)) {
                return;
            }

            Optional<Flight> flightOptional = flightRepository.findByFlightNumberAndDepartureTime(
                    feignDto.getFlightNumber(),
                    feignDto.getDepartureTime()
            );

            if (flightOptional.isEmpty()) {
                List<Airport> airports = checkAirports(feignDto);

                Flight flight = new Flight();
                flight.setFlightNumber(feignDto.getFlightNumber());
                flight.setDepartureAirport(airports.get(0));
                flight.setArrivalAirport(airports.get(1));
                flight.setDepartureTime(feignDto.getDepartureTime());
                flight.setArrivalTime(feignDto.getArrivalTime());
                flight.setPrice(feignDto.getPrice());
                flightRepository.save(flight);
            }
        });
        log.info("Data pulled successfully");
    }

    private List<Airport> checkAirports(FeignDto feignDto) {
        Airport dep = checkDepartureAirport(feignDto);
        Airport arr = checkArrivalAirport(feignDto);
        return List.of(dep, arr);
    }

    private Airport checkArrivalAirport(FeignDto feignDto) {
        Airport arr;
        Optional<Airport> arrivalAirport = airportRepository.findByName(feignDto.getArrivalAirport());
        if (arrivalAirport.isEmpty()) {
            Airport airport = new Airport();
            airport.setName(feignDto.getArrivalAirport());
            airport.setCity(feignDto.getArrivalCity());
            airportRepository.save(airport);
            arr = airport;
        }else {
            arr = arrivalAirport.get();
        }
        return arr;
    }

    private Airport checkDepartureAirport(FeignDto feignDto) {
        Airport dep;
        Optional<Airport> departureAirport = airportRepository.findByName(feignDto.getDepartureAirport());
        if (departureAirport.isEmpty()) {
            Airport airport = new Airport();
            airport.setName(feignDto.getDepartureAirport());
            airport.setCity(feignDto.getDepartureCity());
            airportRepository.save(airport);
            dep = airport;
        }else {
            dep = departureAirport.get();
        }
        return dep;
    }
}
