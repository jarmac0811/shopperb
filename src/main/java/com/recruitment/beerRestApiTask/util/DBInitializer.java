package com.recruitment.beerRestApiTask.util;

import com.recruitment.beerRestApiTask.services.DataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ConditionalOnProperty(name="app-db-init", havingValue = "true")
public class DBInitializer implements CommandLineRunner {
    @Autowired
    private DataSourceService dataSourceService;

    @Override
    public void run(String... args) throws Exception {
        dataSourceService.initUploadToDB();
        log.info("Database initialized");

    }
}
