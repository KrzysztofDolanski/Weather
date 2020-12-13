package com.sda.weather.weather;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConnectionWeatherRepository extends JpaRepository<ConnectionWeather, Long> {

    List<ConnectionWeather> findConnectionWeatherByPressure(Double pressure);

}
