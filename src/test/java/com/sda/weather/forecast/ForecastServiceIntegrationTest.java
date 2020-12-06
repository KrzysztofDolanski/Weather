package com.sda.weather.forecast;

import com.sda.weather.localisation.Localisation;
import com.sda.weather.localisation.LocalisationFetchService;
import com.sda.weather.localisation.LocalisationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.configuration.injection.MockInjection;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class ForecastServiceIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LocalisationRepository localisationRepository;

    @Test
    void getForecast_returns200StatusCode() throws Exception {
        // given
        Localisation localisation = new Localisation();
        localisation.setCity("Warsaw");
        localisation.setRegion("Mazowieckie");
        localisation.setCountry("Poland");
        localisation.setLat(50.0f);
        localisation.setLon(50.0f);
        Long id = localisationRepository.save(localisation).getId();
        MockHttpServletRequestBuilder request = get("/localise/" + id + "/forecast");

        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
