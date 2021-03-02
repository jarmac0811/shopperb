package com.recruitment.beerRestApiTask;

import com.recruitment.beerRestApiTask.domain.Beer;
import com.recruitment.beerRestApiTask.services.DataSourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeerController {
    private DataSourceService dataSourceService;

    public BeerController(DataSourceService dataSourceService) {
        this.dataSourceService = dataSourceService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Beer>> getBeers() {
        return ResponseEntity.ok().body(dataSourceService.findBeers());
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void removeBeer(@PathVariable String id) {
        dataSourceService.removeBeer(id);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Beer> sendUserBeer(@RequestBody Beer beer) {
        Beer beer1 = dataSourceService.saveUserBeer(beer);
        URI location = URI.create(String.format("/beers/%d", beer1.getInternalId()));
            return ResponseEntity.created(location).body(beer1);
    }

    @GetMapping("/foodpairing/search/{pairing}")
    @ResponseBody
    public ResponseEntity<List<Beer>> getBeersByFoodPairing(@PathVariable String pairing) {
        return ResponseEntity.ok().body(dataSourceService.fetchBeersByPairing(pairing));
    }

    @GetMapping("/initdb")
    public void initdb() {
        dataSourceService.initUploadToDB();
    }

    @GetMapping("/update")
    public void updateBeersToDB() {
        dataSourceService.updateLocalDatabase();
    }


/*    @GetMapping("memory-status")
    public MemoryStats getMemoryStatistics() {
        MemoryStats stats = new MemoryStats();
        stats.setHeapSize(Runtime.getRuntime().totalMemory());
        stats.setHeapMaxSize(Runtime.getRuntime().maxMemory());
        stats.setHeapFreeSize(Runtime.getRuntime().freeMemory());
        return stats;
    }*/
}
