package com.casestudy.amadeusflightservice.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class FlightRequest {
    @NotBlank(message = "flightNumber field cannot be empty")
    private String flightNumber;
    @NotNull(message = "There must be a departure airport")
    private Long departureAirport;
    @NotNull(message = "There must be an arrival airport")
    private Long arrivalAirport;
    @NotNull(message = "departureTime field cannot be empty")
    @Future(message = "departureTime must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime departureTime;
    @NotNull(message = "arrivalTime field cannot be empty")
    @Future(message = "arrivalTime must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime arrivalTime;
    @NotNull(message = "price field cannot be empty")
    private BigDecimal price;
}
