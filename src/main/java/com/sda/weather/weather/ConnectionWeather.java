package com.sda.weather.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sda.weather.localisation.Localisation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionWeather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double temp;
    private Double feels_like;
    private Double temp_min;
    private Double temp_max;
    private Double pressure;
    private Double sea_level;
    private Double grnd_level;
    private Double humidity;
    private Double temp_kf;

    @ManyToOne
    private Localisation localisation;
}
