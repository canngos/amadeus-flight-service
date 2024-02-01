package com.casestudy.amadeusflightservice.service;

import com.casestudy.amadeusflightservice.dto.FlightDto;
import com.casestudy.amadeusflightservice.entity.Airport;
import com.casestudy.amadeusflightservice.entity.Flight;
import com.casestudy.amadeusflightservice.exception.BusinessException;
import com.casestudy.amadeusflightservice.exception.Status;
import com.casestudy.amadeusflightservice.exception.TransactionCode;
import com.casestudy.amadeusflightservice.repository.AirportRepository;
import com.casestudy.amadeusflightservice.repository.FlightRepository;
import com.casestudy.amadeusflightservice.request.FlightRequest;
import com.casestudy.amadeusflightservice.response.DefaultMessageResponse;
import com.casestudy.amadeusflightservice.response.FlightResponse;
import com.casestudy.amadeusflightservice.response.base.BaseBody;
import com.casestudy.amadeusflightservice.response.body.DefaultMessageBody;
import com.casestudy.amadeusflightservice.response.body.FlightResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    @Override
    public FlightResponse getFlights() {
        List<Flight> flights = flightRepository.findAll();

        List<FlightDto> flightDtoList = FlightDto.convertToFlightDto(flights);

        FlightResponse flightResponse = new FlightResponse();
        FlightResponseBody body = new FlightResponseBody();
        body.setFlights(flightDtoList);
        flightResponse.setBody(new BaseBody<>(body));
        flightResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return flightResponse;
    }

    @Override
    public DefaultMessageResponse addFlight(FlightRequest flightRequest) {
        Optional<Flight> flightOptional = flightRepository.findByFlightNumberAndDepartureTime(
                flightRequest.getFlightNumber(),
                flightRequest.getDepartureTime()
        );

        if (flightOptional.isPresent()) {
            throw new BusinessException(TransactionCode.FLIGHT_ALREADY_EXISTS);
        }

        List<Airport> airports = checkAirports(flightRequest.getDepartureAirport(), flightRequest.getArrivalAirport());

        Flight flight = new Flight();
        BeanUtils.copyProperties(flightRequest, flight);
        flight.setDepartureAirport(airports.get(0));
        flight.setArrivalAirport(airports.get(1));
        flightRepository.save(flight);

        DefaultMessageResponse defaultMessageResponse = new DefaultMessageResponse();
        DefaultMessageBody body = new DefaultMessageBody("Flight added successfully");
        defaultMessageResponse.setBody(new BaseBody<>(body));
        defaultMessageResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return defaultMessageResponse;
    }

    @Override
    public DefaultMessageResponse updateFlight(Long id, FlightRequest flightRequest) {
        Optional<Flight> flightOptional = flightRepository.findById(id);
        if (flightOptional.isEmpty()) {
            throw new BusinessException(TransactionCode.FLIGHT_NOT_FOUND);
        }

        List<Airport> airports = checkAirports(flightRequest.getDepartureAirport(), flightRequest.getArrivalAirport());

        Flight flight = flightOptional.get();
        BeanUtils.copyProperties(flightRequest, flight);
        flight.setDepartureAirport(airports.get(0));
        flight.setArrivalAirport(airports.get(1));
        flightRepository.save(flight);

        DefaultMessageResponse defaultMessageResponse = new DefaultMessageResponse();
        DefaultMessageBody body = new DefaultMessageBody("Flight updated successfully");
        defaultMessageResponse.setBody(new BaseBody<>(body));
        defaultMessageResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return defaultMessageResponse;
    }

    @Override
    public DefaultMessageResponse deleteFlight(Long id) {
        Optional<Flight> flightOptional = flightRepository.findById(id);
        if (flightOptional.isEmpty()) {
            throw new BusinessException(TransactionCode.FLIGHT_NOT_FOUND);
        }
        flightRepository.deleteById(id);

        DefaultMessageResponse defaultMessageResponse = new DefaultMessageResponse();
        DefaultMessageBody body = new DefaultMessageBody("Flight deleted successfully");
        defaultMessageResponse.setBody(new BaseBody<>(body));
        defaultMessageResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return defaultMessageResponse;
    }

    private List<Airport> checkAirports(Long departureAirport, Long arrivalAirport) {
        if (departureAirport.equals(arrivalAirport)) {
            throw new BusinessException(TransactionCode.SAME_AIRPORTS);
        }

        Airport departure = airportRepository.findById(departureAirport)
                    .orElseThrow(() -> new BusinessException(TransactionCode.AIRPORT_NOT_FOUND));
        Airport arrival = airportRepository.findById(arrivalAirport)
                    .orElseThrow(() -> new BusinessException(TransactionCode.AIRPORT_NOT_FOUND));

        return List.of(departure, arrival);
    }
}
