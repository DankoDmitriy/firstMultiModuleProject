package com.epam.grow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication ()
public class RestApp {
    public static void main(String[] args) {
//        SpringApplication.run(RestApp.class, args);
        SpringApplication app = new SpringApplication(RestApp.class);
        app.setDefaultProperties(Collections.<String, Object>singletonMap("server.port","8081"));
        app.run(args);
    }
}
