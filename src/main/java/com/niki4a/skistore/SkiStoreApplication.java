package com.niki4a.skistore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SkiStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkiStoreApplication.class, args);
    }

}
