package com.casestudy.amadeusflightservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_generator")
    @SequenceGenerator(name = "flight_generator", sequenceName = "flight_seq", initialValue = 20)
    private Long id;
    @Column(nullable = false)
    private String flightNumber;
    @ManyToOne
    private Airport departureAirport;
    @ManyToOne
    private Airport arrivalAirport;
    @Column(nullable = false)
    private LocalDateTime departureTime;
    @Column(nullable = false)
    private LocalDateTime arrivalTime;
    @Column(nullable = false)
    private BigDecimal price;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
