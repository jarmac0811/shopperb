package com.recruitment.beerRestApiTask.IntegrationTests;

import com.recruitment.beerRestApiTask.domain.Beer;
import com.recruitment.beerRestApiTask.repositories.BeerRepository;
import com.recruitment.beerRestApiTask.services.DataSourceService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RestApiTaskApplication.class)
@Import(DataSourceService.class)
@SpringBootTest
public class RestApiTaskApplicationITTests {

    @Autowired
    private DataSourceService dataSourceService;
    @Autowired
    private BeerRepository beerRepository;


    @Test
    @Disabled
    public void testGetBeers() {
        ResponseEntity<Beer[]> beersFromPunkAPI = dataSourceService.fetchFromPunkAPI();
        MatcherAssert.assertThat(beersFromPunkAPI.getBody(), IsInstanceOf.instanceOf(Beer[].class));
    }

    @Test
    @Transactional
    public void testUploadBeers() {
        dataSourceService.initUploadToDB();
        System.out.println("beerRepository.findByName(\"Buzz\") = " + beerRepository.findByName("Buzz"));
        //assertNotNull(beerRepository.findByName("ma"));
    }

    @Test
    public void testReadFromFile() throws IOException {
        String pathToFile = "testUserInputData.txt";
        List<Beer> beersFromFile = dataSourceService.readFromFile();
        System.out.println("beersFromFile = " + beersFromFile);
        assertNotNull(beersFromFile);
    }

    @Test
    public void testSaveDataToFile() throws IOException {
        dataSourceService.saveUserDataToFile(new Beer());
    }
    /*
    @Test
    public void testGetBeersV2() {
        ResponseEntity<Beer[]> beersFromPunkAPI = punkApiClient.fetchBeers();

    }
*/
}
