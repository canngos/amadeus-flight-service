package com.casestudy.amadeusflightservice.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class FlightRequest {
    @NotBlank(message = "There must be a departure airport")
    private Long departureAirport;
    @NotBlank(message = "There must be an arrival airport")
    private Long arrivalAirport;
    @NotBlank(message = "departureTime field cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime departureTime;
    @NotBlank(message = "arrivalTime field cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime arrivalTime;
    @NotNull(message = "price field cannot be empty")
    private BigDecimal price;
}
