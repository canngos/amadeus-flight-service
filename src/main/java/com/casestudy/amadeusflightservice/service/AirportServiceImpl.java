package com.casestudy.amadeusflightservice.service;

import com.casestudy.amadeusflightservice.dto.AirportDto;
import com.casestudy.amadeusflightservice.entity.Airport;
import com.casestudy.amadeusflightservice.exception.BusinessException;
import com.casestudy.amadeusflightservice.exception.Status;
import com.casestudy.amadeusflightservice.exception.TransactionCode;
import com.casestudy.amadeusflightservice.repository.AirportRepository;
import com.casestudy.amadeusflightservice.request.AirportRequest;
import com.casestudy.amadeusflightservice.response.AirportResponse;
import com.casestudy.amadeusflightservice.response.DefaultMessageResponse;
import com.casestudy.amadeusflightservice.response.base.BaseBody;
import com.casestudy.amadeusflightservice.response.body.AirportResponseBody;
import com.casestudy.amadeusflightservice.response.body.DefaultMessageBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    @Override
    public AirportResponse getAirports() {
        List<Airport> airports = airportRepository.findAll();

        List<AirportDto> airportDtoList = convertAirportListToDto(airports);

        AirportResponse airportResponse = new AirportResponse();
        AirportResponseBody body = new AirportResponseBody();
        body.setAirports(airportDtoList);
        airportResponse.setBody(new BaseBody<>(body));
        airportResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return airportResponse;
    }

    @Override
    public DefaultMessageResponse addAirport(AirportRequest airportRequest) {
        Optional<Airport> airportOptional = airportRepository.findByName(airportRequest.getName());
        if (airportOptional.isPresent()) {
            throw new BusinessException(TransactionCode.AIRPORT_ALREADY_EXISTS);
        }

        Airport airport = new Airport();
        BeanUtils.copyProperties(airportRequest, airport);
        airportRepository.save(airport);

        DefaultMessageResponse defaultMessageResponse = new DefaultMessageResponse();
        DefaultMessageBody body = new DefaultMessageBody("Airport added successfully");
        defaultMessageResponse.setBody(new BaseBody<>(body));
        defaultMessageResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return defaultMessageResponse;
    }

    @Override
    public DefaultMessageResponse updateAirport(Long id, AirportRequest airportRequest) {
        Optional<Airport> airportOptional = airportRepository.findById(id);

        if (airportOptional.isEmpty()) {
            throw new BusinessException(TransactionCode.AIRPORT_NOT_FOUND);
        }

        Airport airport = airportOptional.get();
        BeanUtils.copyProperties(airportRequest, airport);
        airportRepository.save(airport);

        DefaultMessageResponse defaultMessageResponse = new DefaultMessageResponse();
        DefaultMessageBody body = new DefaultMessageBody("Airport updated successfully");
        defaultMessageResponse.setBody(new BaseBody<>(body));
        defaultMessageResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return defaultMessageResponse;
    }

    @Override
    public DefaultMessageResponse deleteAirport(Long id) {
        Optional<Airport> airportOptional = airportRepository.findById(id);

        if (airportOptional.isEmpty()) {
            throw new BusinessException(TransactionCode.AIRPORT_NOT_FOUND);
        }

        airportRepository.deleteById(id);

        DefaultMessageResponse defaultMessageResponse = new DefaultMessageResponse();
        DefaultMessageBody body = new DefaultMessageBody("Airport deleted successfully");
        defaultMessageResponse.setBody(new BaseBody<>(body));
        defaultMessageResponse.setStatus(new Status(TransactionCode.SUCCESS));
        return defaultMessageResponse;
    }

    private List<AirportDto> convertAirportListToDto(List<Airport> airports) {
        return airports.stream()
                .map(airport -> {
                    AirportDto airportDto = new AirportDto();
                    BeanUtils.copyProperties(airport, airportDto);
                    return airportDto;
                })
                .toList();
    }
}
