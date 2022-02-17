package com.example.numbergenerator;

import com.example.numbergenerator.controller.NumberRestController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
record SmokeTest(NumberRestController restController) {

    @Autowired
    SmokeTest {
    }

    @Test
    void contextLoads() {
        Assertions.assertNotNull(restController);
    }

}