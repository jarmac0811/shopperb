package com.recruitment.beerRestApiTask.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class UserOrder {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String userId;
    private String datePlaced;
    @Embedded
    @NotNull
    private Shipping shipping;
    @NotNull
    @Min(2)
    private String totalPrice;
    /*    @ElementCollection
        @CollectionTable(name = "cart_item_mapping",
                joinColumns = {@JoinColumn(name = "cart_id", referencedColumnName = "id")})
        @MapKeyColumn(name = "cart_id")
        @Column(name = "cart_item_id")
        @JsonProperty("productsQuantityMap")*/
//    @ElementCollection
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userOrder", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<OrderItem> items = new HashSet();
}
