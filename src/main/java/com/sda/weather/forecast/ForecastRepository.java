package com.sda.weather.forecast;

import com.sda.weather.security.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForecastRepository extends JpaRepository<Forecast, Long> {

}
