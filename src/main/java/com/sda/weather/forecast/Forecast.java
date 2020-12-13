package com.sda.weather.forecast;

import com.sda.weather.localisation.Localisation;
import com.sda.weather.weather.ConnectionWeather;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant createdDate;
    private Double temperature;
    private Double pressure;
    private Double humidity;
    private Double windDagre;
    private Double windSpeed;


    @ManyToOne
    @MapKey(name = "id")
    Localisation localisation;

    @ManyToOne
    ConnectionWeather connectionWeather;

}
