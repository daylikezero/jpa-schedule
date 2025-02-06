package com.example.jpaschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JpaScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaScheduleApplication.class, args);
    }

}
