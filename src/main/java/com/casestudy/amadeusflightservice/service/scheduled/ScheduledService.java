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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledService {

    private final FeignDataPullService feignDataPullService;
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    @Scheduled(cron = "0 0 12 * * *") // every day at 12 AM
    public void pullData() {
        log.info("Pulling data from mockapi");
        List<FeignDto> flights = feignDataPullService.getFlights();

        flights.forEach(feignDto -> {
            List<Airport> airports = checkAirports(feignDto);

            Flight flight = new Flight();
            flight.setDepartureAirport(airports.get(0));
            flight.setArrivalAirport(airports.get(1));
            flight.setDepartureTime(feignDto.getDepartureTime());
            flight.setArrivalTime(feignDto.getArrivalTime());
            flight.setPrice(feignDto.getPrice());
            flightRepository.save(flight);
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