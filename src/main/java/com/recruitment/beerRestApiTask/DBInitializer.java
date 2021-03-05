package com.recruitment.beerRestApiTask;

import com.recruitment.beerRestApiTask.services.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class DBInitializer implements CommandLineRunner {
    @Autowired
    private DataSourceService dataSourceService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("initialized");
        dataSourceService.initUploadToDB();

    }
}
