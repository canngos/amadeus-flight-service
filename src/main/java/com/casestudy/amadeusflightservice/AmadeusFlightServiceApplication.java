package com.casestudy.amadeusflightservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // to enable feign client
public class AmadeusFlightServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmadeusFlightServiceApplication.class, args);
	}

}
