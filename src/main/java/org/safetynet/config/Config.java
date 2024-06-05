package org.safetynet.config;

import org.safetynet.repository.impl.DataLoadJson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.io.PrintWriter;

@Configuration
public class Config {

    @Bean
    CommandLineRunner cleanLogsAndStartDatabase(){
        return args -> {
            new PrintWriter("src/main/resources/log/app.log").close();
            DataLoadJson.init();
        };
    }
}
