package com.sda.weather.forecast;

import com.sda.weather.exeptions.NotFoundLocalisationException;
import com.sda.weather.forecast.client.ForecastClient;
import com.sda.weather.localisation.Localisation;
import com.sda.weather.localisation.LocalisationFetchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ForecastService {

    private final LocalisationFetchService localisationFetchService;
    private final ForecastRepository forecastRepository;
    private final ForecastClient forecastClient;

    public Forecast getForecast(Long id, Integer period) {
        Localisation localisation = localisationFetchService.getLocalisation(id);
        String city = localisation.getCity();
        LocalDate forecastDate = LocalDate.now().plusDays(period);


        Forecast forecast = forecastClient.getForecast(city, forecastDate)
                .orElseThrow(() -> new NotFoundLocalisationException("Forecast for city " + city + " not found"));

        forecast.setCreatedDate(Instant.now());
        forecast.setLocalisation(localisation);

        return forecastRepository.save(forecast);

    }
}

