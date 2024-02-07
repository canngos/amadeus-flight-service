package com.casestudy.amadeusflightservice.service;

import com.casestudy.amadeusflightservice.dto.FlightDto;
import com.casestudy.amadeusflightservice.entity.Airport;
import com.casestudy.amadeusflightservice.entity.Flight;
import com.casestudy.amadeusflightservice.exception.BusinessException;
import com.casestudy.amadeusflightservice.exception.Status;
import com.casestudy.amadeusflightservice.exception.TransactionCode;
import com.casestudy.amadeusflightservice.repository.AirportRepository;
import com.casestudy.amadeusflightservice.repository.FlightRepository;
import com.casestudy.amadeusflightservice.response.FlightResponse;
import com.casestudy.amadeusflightservice.response.base.BaseBody;
import com.casestudy.amadeusflightservice.response.body.FlightResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    @Override
    public FlightResponse searchFlights(String departureAirport, String arrivalAirport, String departureDate) {
        Optional<Airport> departureAirportOptional = airportRepository.findByName(departureAirport);
        Optional<Airport> arrivalAirportOptional = airportRepository.findByName(arrivalAirport);

        if (departureAirportOptional.isEmpty() || arrivalAirportOptional.isEmpty()) {
            throw new BusinessException(TransactionCode.AIRPORT_NOT_FOUND);
        }

        List<Flight> flights = flightRepository.findByDepartureAirportAndArrivalAirport(departureAirportOptional.get(), arrivalAirportOptional.get());

        List<Flight> filtered = filterFlightsByDepartureDate(flights, departureDate);

        List<FlightDto> flightDtos = FlightDto.convertToFlightDto(filtered);

        FlightResponse flightResponse = new FlightResponse();
        FlightResponseBody body = new FlightResponseBody();
        body.setFlights(flightDtos);
        flightResponse.setBody(new BaseBody<>(body));
        flightResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return flightResponse;

    }
    @Override
    public FlightResponse searchFlights(String departureAirport, String arrivalAirport, String departureDate, String returnDate) {
        List<FlightDto> departureFlightDtos = searchFlights(departureAirport, arrivalAirport, departureDate).getBody().getData().getFlights();
        List<FlightDto> returnFlightDtos = searchFlights(arrivalAirport, departureAirport, returnDate).getBody().getData().getFlights();

        List<FlightDto> flightDtos = new ArrayList<>();
        flightDtos.addAll(departureFlightDtos);
        flightDtos.addAll(returnFlightDtos);

        FlightResponse flightResponse = new FlightResponse();
        FlightResponseBody body = new FlightResponseBody();
        body.setFlights(flightDtos);
        flightResponse.setBody(new BaseBody<>(body));
        flightResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return flightResponse;
    }

    @Override
    public FlightResponse searchFlightsByCity(String departureCity, String arrivalCity, String departureDate) {
        List<Airport> departureAirports = airportRepository.findByCity(departureCity);
        List<Airport> arrivalAirports = airportRepository.findByCity(arrivalCity);

        List<FlightDto> flightDtos = getFlights(departureDate, departureAirports, arrivalAirports);
        FlightResponse flightResponse = new FlightResponse();
        FlightResponseBody body = new FlightResponseBody();
        body.setFlights(flightDtos);
        flightResponse.setBody(new BaseBody<>(body));
        flightResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return flightResponse;
    }

    @Override
    public FlightResponse searchFlightsByCity(String departureCity, String arrivalCity, String departureDate, String returnDate) {
        List<FlightDto> departureFlightDtos = searchFlightsByCity(departureCity, arrivalCity, departureDate).getBody().getData().getFlights();
        List<FlightDto> returnFlightDtos = searchFlightsByCity(arrivalCity, departureCity, returnDate).getBody().getData().getFlights();

        List<FlightDto> flightDtos = new ArrayList<>();
        flightDtos.addAll(departureFlightDtos);
        flightDtos.addAll(returnFlightDtos);

        FlightResponse flightResponse = new FlightResponse();
        FlightResponseBody body = new FlightResponseBody();
        body.setFlights(flightDtos);
        flightResponse.setBody(new BaseBody<>(body));
        flightResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return flightResponse;
    }

    private List<Flight> filterFlightsByDepartureDate(List<Flight> flights, String departureDate) {
        LocalDate depDate = parseDate(departureDate);
        return flights.stream()
                .filter(flight -> {
                    LocalDate formattedDepartureTime = flight.getDepartureTime().toLocalDate();
                    return formattedDepartureTime.equals(depDate);
                })
                .sorted(Comparator.comparing(Flight::getDepartureTime))
                .toList();
    }

    private static LocalDate parseDate(String departureDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate depDate;
        try {
            depDate = LocalDate.parse(departureDate, formatter);
        } catch (Exception e) {
            throw new BusinessException(TransactionCode.INVALID_DATE_FORMAT);
        }
        return depDate;
    }

    private List<FlightDto> getFlights(String departureDate, List<Airport> departureAirports, List<Airport> arrivalAirports) {
        List<FlightDto> flightDtos = new ArrayList<>();
        departureAirports.forEach(departureAirport -> arrivalAirports.forEach(arrivalAirport -> {
            List<FlightDto> flights = searchFlights(departureAirport.getName(), arrivalAirport.getName(), departureDate).getBody().getData().getFlights();
            flightDtos.addAll(flights);
        }));
        return flightDtos.stream().sorted(Comparator.comparing(FlightDto::getDepartureTime)).toList();
    }
}
