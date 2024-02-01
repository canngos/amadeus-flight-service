package com.casestudy.amadeusflightservice.service.scheduled;

import com.casestudy.amadeusflightservice.dto.FeignDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "data-pull-service", url = "https://65ba0cd5b4d53c066551eb64.mockapi.io")
public interface FeignDataPullService {

    @GetMapping("/mockapi/v1/flights")
    List<FeignDto> getFlights();
}
