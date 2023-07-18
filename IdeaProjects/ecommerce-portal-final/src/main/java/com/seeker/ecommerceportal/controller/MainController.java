package com.seeker.ecommerceportal.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class MainController {
    @Value("${custom.message}")
    private String welcomeMsg;
    @GetMapping("/")
    public String greeting(){
        return welcomeMsg;
    }
}