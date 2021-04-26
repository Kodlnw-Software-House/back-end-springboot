package com.int221.finalproject.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HealthController {

    @RequestMapping("/health")
    public ResponseEntity healthCheck(){
        return new ResponseEntity("This service is healthy", HttpStatus.OK);
    }

}
