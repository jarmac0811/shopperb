package com.recruitment.beerRestApiTask.services;

import com.recruitment.beerRestApiTask.domain.Beer;
import com.recruitment.beerRestApiTask.domain.Food;
import com.recruitment.beerRestApiTask.repositories.BeerRepository;
import com.recruitment.beerRestApiTask.repositories.FoodRepository;
import com.recruitment.beerRestApiTask.util.ListToString;
import com.recruitment.beerRestApiTask.util.ParseList;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DataSourceService {
    private static final String URL = "https://api.punkapi.com/v2/beers";

    private RestTemplate restTemplate;
    private BeerRepository beerRepository;
    private FoodRepository foodRepository;

    public DataSourceService(BeerRepository beerRepository, FoodRepository foodRepository, RestTemplate restTemplate) {
        this.beerRepository = beerRepository;
        this.foodRepository = foodRepository;
        this.restTemplate = restTemplate;
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    public List<Beer> fetchBeersByPairing(String pairing) {
        return beerRepository.findByFoodPairing(pairing);
    }

    public List<Beer> findBeers() {
        return beerRepository.findAll();
    }

    public void removeBeer(String id) {
        beerRepository.deleteById(Long.valueOf(id));
    }

    public Beer saveUserBeer(Beer beer) {
        beer.setIdType("userInput");
        List<Food> foodPairings = beer.getFoodPairing();
        foodPairings.forEach(food -> {
            food.setBeer(beer);
        });
        try {
            saveUserDataToFile(beer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Beer beer1 = beerRepository.save(beer);
        beer1.setExternalId(beer1.getInternalId());
        return beer1;
    }

    public ResponseEntity<Beer[]> fetchFromPunkAPI() {
        ResponseEntity<Beer[]> response = restTemplate.getForEntity(URL, Beer[].class);
        Beer[] beers = response.getBody();
        return response;

        /* Alternative approach with explicit use of request
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> request = new HttpEntity<>("parameters", headers);
        ResponseEntity<List<Beer>> response = restTemplate.exchange(URL, HttpMethod.GET, request, new ParameterizedTypeReference<List<Beer>>() {});
*/
    }

    @Transactional
    public void initUploadToDB() {
        ResponseEntity<Beer[]> beers = fetchFromPunkAPI();
/*        Beer beer = beers.getBody()[0];
        List<Food> foodList = beer.getFoodPairing();
        Beer beer1 = beerRepository.save(beers.getBody()[0]);
        Beer beer2 = beerRepository.save(beers.getBody()[1]);
        Food food1 = foodList.get(0);
        Food food2 = foodList.get(1);
        food1.setBeer(beer1);
        food2.setBeer(beer2);*/
        List<Beer> beersList = Arrays.asList(beers.getBody());
        beersList.forEach(b -> {
                    b.setIdType("punkApi");
                    beerRepository.save(b);
                    saveFood(b);
                }
        );
        List<Beer> beersFromUser = null;
        try {
            beersFromUser = readFromFile();
            beersFromUser.forEach(b -> {
                        beerRepository.save(b);
                        saveFood(b);
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFood(Beer beerin) {
        List<Food> foodList = beerin.getFoodPairing();
        foodList.forEach(f -> {
            f.setBeer(beerin);
            foodRepository.save(f);
        });
    }

    @Transactional
    public void updateLocalDatabase() {
        ResponseEntity<Beer[]> beersFromPunkAPI = fetchFromPunkAPI();
        Beer beer = beersFromPunkAPI.getBody()[0];
        updateFromPunkApi(beer);
        //TODO replace single with multiple save
//      Arrays.asList(beersFromPunkAPI.getBody()).forEach(b->compareAndUpdate(b));

    }

    /*    public List<Beer> readFromFile() throws IOException {
            String csvFilePath = "src/main/resources/userInputData.txt";
            Path path= Paths.get(csvFilePath);
    //        Files.write();
    //        System.out.println(new File("").getAbsolutePath());
    //        File file = new File(csvFilePath);
    //        System.out.println("Check me! '" + file.getAbsolutePath() + "'");
    //        System.out.println(file.exists());
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText;
            List<Beer> beers = new ArrayList<>();
            Beer beer;

            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                beer = new Beer();
                beer.setIdType("userInput");
                beer.setName(data[1]);
                beer.setTagline(data[1]);
                beer.setDescription(data[2]);
                beer.setFirst_brewed(data[3]);
                //beer.setIbu(Integer.parseInt(data[4]));
                //beer.setFood_pairing(data[5]);
                beers.add(beer);
            }
            lineReader.close();
            return beers;
        }*/

    public List<Beer> readFromFile() throws IOException {
        String csvBeerFilePath = "src/main/resources/userInputData.csv";
        List<Beer> beers = new ArrayList<>();
        String[] csvHeaders = {"idType", "description", "name", "firstbrewed", "ibu", "tagline", "foodpairing"};
        //TODO integrate headers with class
        try (ICsvBeanReader beanReader1 = new CsvBeanReader(new FileReader(csvBeerFilePath), CsvPreference.STANDARD_PREFERENCE)) {
            final String[] headers1 = beanReader1.getHeader(true);
            final CellProcessor[] processors1 = getProcessors1();
            Beer beer;
            while ((beer = beanReader1.read(Beer.class, headers1, processors1)) != null) {
                beers.add(beer);
            }
        }

        return beers;
    }

    private CellProcessor[] getProcessors1() {
        final CellProcessor[] processors = new CellProcessor[]{
//                new ParseLong(), // foreignId
                new NotNull(), // idType
                new NotNull(), // descryption
                new NotNull(), // name
                new NotNull(), // first_brewed
                new NotNull(new ParseInt()), // ibu
                new NotNull(), // tagline
                new ParseList()  //pairings
        };
        return processors;
    }

    private CellProcessor[] getProcessors2() {
        final CellProcessor[] processors = new CellProcessor[]{
//                new ParseLong(), // foreignId
                new NotNull(), // idType
                new NotNull(), // descryption
                new NotNull(), // name
                new NotNull(), // first_brewed
                new NotNull(new ParseInt()), // ibu
                new NotNull(), // tagline
                new ListToString()  //pairings
        };
        return processors;
    }

    public void saveUserDataToFile(Beer beer) throws IOException {
        String csvFilePath = "src/main/resources/userInputData.csv";
        File file = new File(csvFilePath);
        CsvBeanWriter beanWriter = new CsvBeanWriter(new FileWriter(csvFilePath, true), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = new String[]{"idType", "description", "name", "firstbrewed", "ibu", "tagline", "foodpairing"};
        if (!file.exists()) {
            file.createNewFile();
            beanWriter.writeHeader(csvHeader);
        }
//        List<Beer> userInputBeers = beerRepository.findByIdType("userInput");
/*        for (Beer beer : userInputBeers) {
        }*/
        beanWriter.write(beer, csvHeader, getProcessors2());
        beanWriter.flush();
        beanWriter.close();


        // List<Beer> userInputBeers = (List<Beer>) beerRepository.findAll();
        //  BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
        //  fileWriter.write("idType,name,tagline,first_brewed,7,first_brewed,ibu,food_pairing");
/*        for (Beer userInputBeer : userInputBeers) {
            int id = userInputBeer.getId();
            int foreignId = userInputBeer.getForeignId();
            String idType = userInputBeer.getIdType();
            String name = userInputBeer.getName();
            String first_brewed = userInputBeer.getFirst_brewed();
            int ibu = userInputBeer.getIbu();
            List<String> food_pairing = userInputBeer.getFood_pairing();
            StringBuilder sb = new StringBuilder();
            food_pairing.forEach(
                   s-> sb.append(s).append(';')
            );
            String line = String.format("%d,%d,%s \"%s\",%s,%d,\"%s\"", id, foreignId, idType, name, first_brewed, ibu, sb.toString());
            fileWriter.newLine();
            fileWriter.write(line);
        }*/
        // fileWriter.close();
    }

    private void updateFromPunkApi(Beer beerRemote) {
        if (isChangedOrAbsent(beerRemote)) {
            beerRepository.save(beerRemote);
            System.out.println("DB updated");
        } else
            System.out.println("Not updated");
    }

    private boolean isChangedOrAbsent(Beer beerRemote) {
        long id = beerRemote.getExternalId();
        Beer beerLocal = beerRepository.findByExternalId(id);
        if (beerLocal == null)
            return true;
        if (!beerLocal.equals(beerRemote)) {
            return true;
        }
        return false;
    }
}

