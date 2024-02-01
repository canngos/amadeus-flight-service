package com.casestudy.amadeusflightservice.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirportRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "City is mandatory")
    private String city;
}
