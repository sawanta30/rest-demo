package com.demo.controller;

import com.demo.aspect.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest/v1")
public class RestController {

    @GetMapping("/ping")
    @Log
    public ResponseEntity<LocalDateTime> ping(){
        return new ResponseEntity<>(LocalDateTime.now(), HttpStatus.OK);
    }

    @GetMapping("/welcome")
    @Log
    public String welcome(@RequestParam String name){
       return "Welcome "+name;
    }

}
