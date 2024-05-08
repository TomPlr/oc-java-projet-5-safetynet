package org.safetynet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafetyNetApplicationTests {


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SafetyNetApplicationTests.class);
        app.run(args);
    }
}
