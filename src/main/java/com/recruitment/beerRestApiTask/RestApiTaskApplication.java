package com.recruitment.beerRestApiTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication//(exclude = HibernateJpaAutoConfiguration.class)
@EnableScheduling
public class
RestApiTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApiTaskApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
