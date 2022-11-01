package com.modsen.meetup.api.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.modsen.meetup.api")
public class MeetupApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MeetupApiApplication.class, args);
    }
}
