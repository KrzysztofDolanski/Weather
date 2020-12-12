package com.sda.weather;

import com.sda.weather.forecast.OpenWeatherProperties;
import com.sda.weather.localisation.WeatherStackProperties;
import com.sda.weather.security.AppUser;
import com.sda.weather.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties ({OpenWeatherProperties.class, WeatherStackProperties.class})
public class WeatherApplication implements CommandLineRunner {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }



    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findAll().size()==0){
            AppUser user1 = new AppUser();
            user1.setUsername("Krzysztof");
            user1.setPassword(passwordEncoder.encode("777"));
            user1.setAuthorities(Collections.singletonList(()->"ROLE_USER"));
            userRepository.save(user1);


            AppUser user2 = new AppUser();
            user1.setUsername("Justyna");
            user1.setPassword(passwordEncoder.encode("23423"));
            user1.setAuthorities(Collections.singletonList(()->"ROLE_ADMIN"));
            userRepository.save(user1);
        }
    }
}
