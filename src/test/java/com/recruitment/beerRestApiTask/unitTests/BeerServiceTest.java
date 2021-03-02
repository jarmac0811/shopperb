package com.recruitment.beerRestApiTask.unitTests;

import com.recruitment.beerRestApiTask.services.DataSourceService;
import com.recruitment.beerRestApiTask.domain.Beer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest
public class BeerServiceTest {

    @InjectMocks
    DataSourceService dataSourceService;

    @Mock
    RestTemplate restTemplate;

    @Test
    public void getBeersFromPunkAPI() {
        Beer[] beerArray = {new Beer(1, "ale", null, null, null, null, 2, null)};
        ResponseEntity<Beer[]> myEntity = new ResponseEntity(beerArray, HttpStatus.ACCEPTED);

        Mockito.when(restTemplate.getForEntity(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.<Class<Beer[]>>any()))
                .thenReturn(myEntity);

        ResponseEntity<Beer[]> res = dataSourceService.fetchFromPunkAPI();
        Assertions.assertEquals(beerArray[0], res.getBody()[0]);
    }
}
