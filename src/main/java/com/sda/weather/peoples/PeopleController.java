package com.sda.weather.peoples;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class PeopleController {


    private PeopleService peopleService;


    @GetMapping("/hello")
    public String hello(Principal principal, Model model) {
        model.addAttribute("name", principal.getName());
        return "weather";
    }


    @GetMapping("/login")
    public String signup(Model model){
        model.addAttribute("username", new People());
        return "login";
    }

    @PostMapping("/login")
    public String register(People people){
        peopleService.addPeople(people);
        return "weather/Warsaw";
    }
}

