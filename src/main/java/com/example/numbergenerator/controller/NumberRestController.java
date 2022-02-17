package com.example.numbergenerator.controller;

import com.example.numbergenerator.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/number")
public class NumberRestController {

    private final NumberService numberService;

    @Autowired
    public NumberRestController(NumberService numberService) {
        this.numberService = numberService;
    }

    @GetMapping("/random")
    public String getRandomNumber() {
        return numberService.getRandomNumber();
    }

    @GetMapping("/next")
    public String getNextNumber() {
        return numberService.getNextNumber();
    }
}
