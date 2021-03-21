package com.recruitment.beerRestApiTask.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Category {

    @Id
    private String categoryId;
    private String categoryName;
}
