package com.sda.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.localisation.Localisation;
import com.sda.weather.localisation.LocalisationDto;
import com.sda.weather.localisation.LocalisationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherApplicationTests {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    LocalisationRepository localisationRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void createNewLocalisation_createsNewLocalisationAndReturn201HttpStatus() throws Exception {
        //when
        localisationRepository.deleteAll();

        LocalisationDto localisationDto = new LocalisationDto("Gdansk", "Polska", "54.347629", "18.6452324", "pomorskie");

        String content = objectMapper.writeValueAsString(localisationDto);
        MockHttpServletRequestBuilder post = post("/localise")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        //given
        MvcResult mvcResult = mockMvc.perform(post).andReturn();

        //then

        MockHttpServletResponse response = mvcResult.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        List<Localisation> all = localisationRepository.findAll();


    }

}
