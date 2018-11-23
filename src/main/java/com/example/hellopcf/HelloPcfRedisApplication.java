package com.example.hellopcf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@SpringBootApplication
@RestController
@EnableCaching
public class HelloPcfRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloPcfRedisApplication.class, args);
    }
}

@Component
class Greeter {
    @Cacheable("hello")
    public String hello() {
        return "Hello, Redis. It's " + OffsetDateTime.now() + " now.";
    }
}