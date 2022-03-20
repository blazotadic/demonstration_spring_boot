package com.example.demonstration_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//@EnableCaching
@SpringBootApplication
public class DemonstrationSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemonstrationSpringBootApplication.class, args);
    }

}
