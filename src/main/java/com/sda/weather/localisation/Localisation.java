package com.sda.weather.localisation;

import com.sda.weather.forecast.Forecast;
import com.sda.weather.weather.ConnectionWeather;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.LuhnCheck;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Data

public class Localisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private Float lat;
    @Column(nullable = false)
    private Float lon;
    @Column(nullable = false)
    private String country;
    @Column
    private String region;

    @Column
    @OneToMany(mappedBy = "localisation")
    List<Forecast> forecast;

    @Column
    @OneToMany(mappedBy = "localisation")
    List<ConnectionWeather> weathers;

    public Optional<String> getRegion() {
        return Optional.ofNullable(region);
    }
}
