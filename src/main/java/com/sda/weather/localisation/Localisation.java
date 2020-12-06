package com.sda.weather.localisation;

import com.sda.weather.forecast.Forecast;
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
    @Column(nullable = false) // todo region can be blank
    private String region;

    public Optional<String> getRegion() {
        return Optional.ofNullable(region);
    }

//    @OneToMany
//    List<Forecast> forecast;
}
