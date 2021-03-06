package com.recruitment.beerRestApiTask.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String price;
    private String category;
    private String imageUrl;
}
