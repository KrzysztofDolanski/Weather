package com.sda.weather;

import com.sda.weather.forecast.client.ForecastClientProperties;
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
@EnableConfigurationProperties({ForecastClientProperties.class, WeatherStackProperties.class})
public class WeatherApplication implements CommandLineRunner {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();

        AppUser user1 = new AppUser();
        user1.setUsername("Krzysztof");
        user1.setPassword(passwordEncoder.encode("777"));
        user1.setAuthorities(Collections.singletonList(() -> "ROLE_USER"));
        userRepository.save(user1);

        AppUser user2 = new AppUser();
        user2.setUsername("Justyna");
        user2.setPassword(passwordEncoder.encode("23423"));
        user2.setAuthorities(Collections.singletonList(() -> "ROLE_ADMIN"));
        userRepository.save(user2);
    }
}
