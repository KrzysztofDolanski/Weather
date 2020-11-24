package com.sda.weather.localisation;

import com.fasterxml.jackson.databind.ObjectMapper;
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
class LocalisationIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocalisationRepository localisationRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void createNewLocalisation_createsNewLocalisationAndReturn201HttpStatus() throws Exception {
        // give
        localisationRepository.deleteAll();
        LocalisationDto localisationDto = new LocalisationDto(null, "Gdansk", "Polska", 54, 33, "pomorskie");
        String content = objectMapper.writeValueAsString(localisationDto);
        MockHttpServletRequestBuilder post = post("/localise")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // when
        MvcResult mvcResult = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = mvcResult.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        List<Localisation> localisations = localisationRepository.findAll();
        assertThat(localisations.size()).isEqualTo(1);
        assertThat(localisations.get(0)).satisfies(localisation -> {
            assertThat(localisation.getCityName()).isEqualTo("Gdansk");
            assertThat(localisation.getCountryName()).isEqualTo("Polska");
            assertThat(localisation.getRegion()).isEqualTo("pomorskie");
            assertThat(localisation.getLatitude()).isEqualTo("54.347629");
            assertThat(localisation.getLongitude()).isEqualTo("18.6452324");
        });
    }

    @Test
    void createNewLocalisation_whenCityIsEmpty_returnsHttpStatus400Code() throws Exception {
        // given
        localisationRepository.deleteAll();
        LocalisationDto localisationDto = new LocalisationDto(null, "", "Polska", 32, 11, "pomorskie");
        String content = objectMapper.writeValueAsString(localisationDto);
        MockHttpServletRequestBuilder post = post("/localise")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // when
        MvcResult mvcResult = mockMvc.perform(post).andReturn();

        // then
        MockHttpServletResponse response = mvcResult.getResponse();
        List<Localisation> localisations = localisationRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(localisations).isEmpty();
    }

    @Test
    void createNewLocalisation_whenCountryIsEmpty_returnsHttpStatus400Code() throws Exception {
        // given
        localisationRepository.deleteAll();
        LocalisationDto localisationDto = new LocalisationDto(null, "Gdansk", "", 23, 87, "pomorskie");
        String content = objectMapper.writeValueAsString(localisationDto);
        MockHttpServletRequestBuilder post = post("/localise")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // when
        MvcResult mvcResult = mockMvc.perform(post).andReturn();

        // then
        MockHttpServletResponse response = mvcResult.getResponse();
        List<Localisation> localisations = localisationRepository.findAll();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(localisations).isEmpty();
    }
}
