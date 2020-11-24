package com.sda.weather.localisation;

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
    private String cityName;
    @Column(nullable = false)
    private int latitude;
    @Column(nullable = false)
    private int longitude;
    @Column(nullable = false)
    private String countryName;
    @Column(nullable = false)
    private String region;

    public Optional<String> getRegion() {
        return Optional.ofNullable(region);
    }
}
