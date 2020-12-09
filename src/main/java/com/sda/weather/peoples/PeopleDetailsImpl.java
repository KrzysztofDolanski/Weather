package com.sda.weather.peoples;

import com.sda.weather.exeptions.PeopleNotFoundException;
import com.sda.weather.peoples.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PeopleDetailsImpl implements UserDetailsService {

    private final PeopleRepository peopleRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return peopleRepository.findPeopleByUsername(s).get();
    }
}
