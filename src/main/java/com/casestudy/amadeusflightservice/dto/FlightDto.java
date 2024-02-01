package com.casestudy.amadeusflightservice.dto;

import com.casestudy.amadeusflightservice.entity.Flight;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class FlightDto {

    private Long id;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private BigDecimal price;

    public static List<FlightDto> convertToFlightDto(List<Flight> filtered) {
        return filtered.stream()
                .map(flight -> {
                    FlightDto flightDto = new FlightDto();
                    BeanUtils.copyProperties(flight, flightDto);
                    flightDto.setDepartureAirport(flight.getDepartureAirport().getName());
                    flightDto.setArrivalAirport(flight.getArrivalAirport().getName());
                    return flightDto;
                })
                .toList();
    }
}
