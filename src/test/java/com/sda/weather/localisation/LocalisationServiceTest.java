package com.sda.weather.localisation;

import com.sda.weather.exeptions.NoCityOrCountryException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocalisationServiceTest {

    @Mock
    LocalisationRepository localisationRepository;

    @InjectMocks
    LocalisationService localisationService;

    @Test
    void createLocalisation_calsLocalisationRepository() {
        // given
        when(localisationRepository.save(any(Localisation.class))).thenReturn(new Localisation());
        LocalisationDefinition data = LocalisationDefinition.builder()
                .cityName("Starogard Gdański")
                .countryName("Polska")
                .longitude("23.13")
                .latitude("13.231")
                .region("mazowieckie")
                .build();

        // when
        Localisation result = localisationService.createLocalisation(data);

        // then
        assertThat(result).isExactlyInstanceOf(Localisation.class);
        verify(localisationRepository, times(1)).save(any(Localisation.class));
    }

    @Test
    void createLocalisation_whenCityOrCountryAreEmpty_throwsNoCityOrCountryException() {
        // given
        LocalisationDefinition data = LocalisationDefinition.builder()
                .cityName("")
                .countryName("Polska")
                .longitude("23.13")
                .latitude("13.231")
                .region("mazowieckie")
                .build();

        // when
        Throwable throwable = catchThrowable(() -> localisationService.createLocalisation(data));

        // then
        assertThat(throwable).isInstanceOf(NoCityOrCountryException.class);
        verify(localisationRepository, times(0)).save(any(Localisation.class));
    }

    @Test
    void createLocalisation_whenCityOrCountryAreBlank_throwsNoCityOrCountryException() {
        // given
        LocalisationDefinition data = LocalisationDefinition.builder()
                .cityName("   ")
                .countryName("Polska")
                .longitude("23.13")
                .latitude("13.231")
                .region("mazowieckie")
                .build();

        // when
        Throwable throwable = catchThrowable(() -> localisationService.createLocalisation(data));

        // then
        assertThat(throwable).isInstanceOf(NoCityOrCountryException.class);
        verify(localisationRepository, times(0)).save(any(Localisation.class));
    }
}
