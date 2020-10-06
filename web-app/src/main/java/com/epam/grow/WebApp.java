package com.epam.grow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class WebApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WebApp.class);
        app.setDefaultProperties(Collections.<String, Object>singletonMap("server.port","8080"));
        app.run(args);
    }
}
