package com.sda.weather.localisation;

import com.sda.weather.forecast.Forecast;
import com.sda.weather.weather.ConnectionWeather;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    private Instant createdDate;
    @CreatedBy
    private String createdBy;
//    @Column
//    @OneToMany(mappedBy = "localisation")
//    List<Forecast> forecast;
//
//    @Column
//    @OneToMany(mappedBy = "localisation")
//    List<ConnectionWeather> weathers;

    public Optional<String> getRegion() {
        return Optional.ofNullable(region);
    }
}
