package com.imola.saraine.lajos.module5.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloWorldController {

    @GetMapping("/")
    public String helloWorld() {
        log.info("Hello world controller is called");
        return "Hello world!";
    }
}
