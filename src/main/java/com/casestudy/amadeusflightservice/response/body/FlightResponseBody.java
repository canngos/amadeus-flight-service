package com.casestudy.amadeusflightservice.response.body;

import com.casestudy.amadeusflightservice.dto.FlightDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FlightResponseBody {

    private List<FlightDto> flights;
}
