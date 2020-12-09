package com.sda.weather.peoples;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeopleRepository extends JpaRepository<People, Long> {

    Optional<People> findPeopleByUsername(String name);
}
