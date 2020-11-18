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
    void createLocalisation_calsLocalisationRepository(){
        when(localisationRepository.save(any(Localisation.class))).thenReturn(new Localisation());

        Localisation result = localisationService.createLocalisation("Starogard Gdański", "Polska", "23.13", "13.231", "mazowieckie");

//        assertThat(result).isExactlyInstanceOf(NoCityOrCountryException.class);
        verify(localisationRepository, times(1)).save(any(Localisation.class));
    }

    @Test
    void createLocalisation_whenCityOrCountry_throwNoCityOrCountryException() {

//        when(localisationRepository.save(any(Localisation.class))).thenReturn(new Localisation());


        Throwable throwable = catchThrowable(() -> localisationService
                .createLocalisation("", "Polska", "23.13", "13.231", "mazowieckie"));

        assertThat(throwable).isInstanceOf(NoCityOrCountryException.class);
        verify(localisationRepository, times(0)).save(any(Localisation.class));

    }
}
