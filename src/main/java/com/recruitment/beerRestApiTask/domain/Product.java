package com.recruitment.beerRestApiTask.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String price;
    private String category;
    private String imageUrl;
}
