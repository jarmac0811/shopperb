package com.recruitment.beerRestApiTask.unitTests;

import com.recruitment.beerRestApiTask.domain.Beer;
import com.recruitment.beerRestApiTask.domain.Food;
import com.recruitment.beerRestApiTask.repositories.BeerRepository;
import com.recruitment.beerRestApiTask.repositories.FoodRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.List;

//@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@TestPropertySource("/application-test.properties")
@DataJpaTest
class BeerRepositoryTests {
    @Autowired
    BeerRepository beerRepository;
    @Autowired
    FoodRepository foodRepository;

    @Test
    void findByName() {
        Beer beer = beerRepository.save(new Beer(10, "Buzz", "tagline", "04/2013", "Our take on the classic", null, 25, null));
        System.out.println("beerRepository.findByName(\"Buzz\") = " + beerRepository.findByName("Buzz"));
        Assertions.assertEquals(beer, beerRepository.findByName("Buzz"));
    }

    @Test
    void findByFoodPairingQuery() {
        Food food = foodRepository.save(new Food("Spicy chicken"));
        Beer beer = beerRepository.save(new Beer(10, "buzz", "tagline", "04/2013", "Our take on the classic", null, 25,
                Arrays.asList(food)));
        food.setBeer(beer);
/*        System.out.println("beerRepository.findByName(\"buzz\") = " + beerRepository.findByName("buzz"));
        System.out.println("foodRepository.findByName(\"Spicy chicken\") = " + foodRepository.findByName("Spicy chicken"));*/

        List<Beer> beersByFood = beerRepository.findByFoodPairing("Spicy chicken");
        System.out.println("beersByFood = " + beersByFood);
        Assertions.assertNotNull(beersByFood);
        Assertions.assertEquals(beer,beersByFood.get(0));
    }

    @Test
    void findByPhones() {
        Beer beer = beerRepository.save(new Beer(10, "buzz", "tagline", "04/2013", "Our take on the classic", null, 25,
                null));
        beer.setPhones(Arrays.asList("Nokia, Samsung, Xiaomi"));
        System.out.println("beerRepository.findByName(\"buzz\") = " + beerRepository.findByName("buzz"));

        List<Beer> beersByPhones = beerRepository.findByPhonesLike("%Nokia%");
        System.out.println("beersByPhones = " + beersByPhones);
        Assertions.assertEquals(beer,beersByPhones.get(0));

    }

}
