package com.casestudy.amadeusflightservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class FeignDto {
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime departureTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime arrivalTime;
    private String departureCity;
    private String arrivalCity;
    private BigDecimal price;
}
