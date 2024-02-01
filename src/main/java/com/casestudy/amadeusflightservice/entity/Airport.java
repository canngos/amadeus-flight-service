package com.casestudy.amadeusflightservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_generator")
    @SequenceGenerator(name = "flight_generator", sequenceName = "flight_seq", initialValue = 20)
    private Long id;
    private String name;
    private String city;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "departureAirport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flight> flights = new ArrayList<>();
}
