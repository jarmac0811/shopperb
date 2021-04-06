package com.recruitment.beerRestApiTask.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@Embeddable
public class Shipping {

    private String name;
    private String line1;
    private String line2;
    private String city;
}
