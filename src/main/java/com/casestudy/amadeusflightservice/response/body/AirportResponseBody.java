package com.casestudy.amadeusflightservice.response.body;

import com.casestudy.amadeusflightservice.dto.AirportDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AirportResponseBody {

    List<AirportDto> airports;
}
