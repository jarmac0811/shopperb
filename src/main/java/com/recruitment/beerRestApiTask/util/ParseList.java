package com.recruitment.beerRestApiTask.util;


import com.recruitment.beerRestApiTask.domain.Food;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.util.CsvContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParseList extends CellProcessorAdaptor {

    public ParseList() {
        super();
    }

    public ParseList(CellProcessor next) {
        // this constructor allows other processors to be chained after ParseDay
        super(next);
    }

    public List<Food> execute(Object value, CsvContext context) {

        validateInputNotNull(value, context);
        List<String> pairings = Arrays.asList(((String) value).split(";"));
        List<Food> foods = new ArrayList<>();
        pairings.forEach(v -> foods.add(new Food(v)));
        return foods;

    }
}