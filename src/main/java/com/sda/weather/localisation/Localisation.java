package com.sda.weather.localisation;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Data

public class Localisation {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private Float lat;
    @Column(nullable = false)
    private Float lon;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String region;

    public Optional<String> getRegion() {
        return Optional.ofNullable(region);
    }
}
