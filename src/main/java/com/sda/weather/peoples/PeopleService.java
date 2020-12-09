package com.sda.weather.peoples;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PeopleService {

    private final PeopleRepository peopleRepository;

    private final PasswordEncoder passwordEncoder;

    public void addPeople(People people) {
        people.setPassword(passwordEncoder.encode(people.getPassword()));
        people.setRole("ROLE_USER");
        peopleRepository.save(people);
    }
}
