package com.recruitment.beerRestApiTask.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ConditionalOnProperty(name = "app-db-init", havingValue = "true")
public class DBInitializer implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("Database initialized");

    }
}
