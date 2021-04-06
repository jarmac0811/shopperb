package com.recruitment.beerRestApiTask.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
public class ShoppingCart {

    @Id
    @GeneratedValue
    private Long id;
    private float totalPrice;
    /*    @ElementCollection
        @CollectionTable(name = "cart_item_mapping",
                joinColumns = {@JoinColumn(name = "cart_id", referencedColumnName = "id")})
        @MapKeyColumn(name = "cart_id")
        @Column(name = "cart_item_id")
        @JsonProperty("productsQuantityMap")*/
//    @ElementCollection
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<CartItem> items = new HashSet();
}
