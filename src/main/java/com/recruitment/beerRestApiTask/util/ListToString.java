package com.recruitment.beerRestApiTask.util;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.util.CsvContext;

import java.util.List;

public class ListToString extends CellProcessorAdaptor {

    public ListToString() {
    }

    public ListToString(CellProcessor next) {
        // this constructor allows other processors to be chained after ParseDay
        super(next);
    }

    public String execute(Object value, CsvContext context) {

        validateInputNotNull(value, context);
        StringBuilder sb = new StringBuilder();
        ((List) value).forEach(v->{sb.append(v);sb.append(";");});// throws an Exception if the input is null
        String pairings = value.toString();
        pairings = pairings.substring(1, pairings.length() - 1);
        return sb.toString();

    }
}