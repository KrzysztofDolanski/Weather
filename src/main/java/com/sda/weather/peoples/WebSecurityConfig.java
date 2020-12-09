package com.sda.weather.peoples;


import com.sda.weather.peoples.PeopleDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Collection;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PeopleDetailsImpl peopleDetails;



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(peopleDetails);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().disable();
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/weather/{city}").permitAll()
                .antMatchers("/localise/{id}/forecast").permitAll()
                .antMatchers("/localise").permitAll()
                .antMatchers("/localiseCity/{city}").permitAll()
                .antMatchers("/sign-up").permitAll()
                .antMatchers("/weather").permitAll()
                .antMatchers("/weather?city={city}").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("root")
                        .password("root")
                        .roles("USER")
                        .build();

        People janusz = People.builder()
                .username("Janusz")
                .password(passwordEncoder().encode("Janusz123"))
                .role("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}

