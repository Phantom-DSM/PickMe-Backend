package com.phantom.pickme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class PickmeApplication {

    private static final String APPLICATION_YML_LOCATIONS = "spring.config.location=" +
            "classpath:application.yml," +
            "/app/config/pickme/application.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(PickmeApplication.class).properties(APPLICATION_YML_LOCATIONS).run(args);
    }
}
