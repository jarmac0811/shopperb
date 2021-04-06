package com.recruitment.beerRestApiTask.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Data
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    private String title;
    private String price;
    private String imageUrl;
    private String quantity;
    @ManyToOne
    @JsonIgnore
    private UserOrder userOrder;
}
