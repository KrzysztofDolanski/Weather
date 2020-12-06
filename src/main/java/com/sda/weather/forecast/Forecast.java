package com.sda.weather.forecast;

import com.sda.weather.localisation.Localisation;
import com.sda.weather.weather.ConnectionWeather;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double temperature;
    Double pressure;
    Double humidity;
    Double windDagre;
    Double windSpeed;
    String date;

    @ManyToOne
    @MapKey(name = "id")
    Localisation localisation;

    @ManyToOne
    ConnectionWeather connectionWeather;

}
