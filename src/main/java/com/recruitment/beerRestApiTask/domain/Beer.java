package com.recruitment.beerRestApiTask.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Data
@NoArgsConstructor
public class Beer implements Serializable {

    @Id
    @GeneratedValue
    @JsonIgnore
    private long internalId;
    @Column(name = "external_id")
    private long externalId;
    @JsonIgnore
    private String idType = "punkApi";
    private String name;
    private String tagline;
    private String firstBrewed;
    @Column(columnDefinition = "text")
    private String description;
    private String imageUrl;
    private int ibu;
    @ElementCollection
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "beer", cascade = {CascadeType.ALL},orphanRemoval = true)
    private List<Food> foodPairing = new ArrayList<Food>();

    @ElementCollection
    @OrderColumn(name = "order_id")
    private List<String> phones = new ArrayList<>();

    @JsonCreator
    public Beer(@JsonProperty("id") int externalId, @JsonProperty("name") String name, @JsonProperty("tagline") String tagline, @JsonProperty("first_brewed") String firstBrewed,
                @JsonProperty("description") String description, @JsonProperty("image_url") String imageUrl, @JsonProperty("ibu") int ibu, @JsonProperty("food_pairing") List<Food> foodPairing) {
        this.externalId = externalId;
        this.name = name;
        this.tagline = tagline;
        this.firstBrewed = firstBrewed;
        this.description = description;
        this.imageUrl = imageUrl;
        this.ibu = ibu;
        this.foodPairing = foodPairing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return externalId == beer.externalId && ibu == beer.ibu && idType.equals(beer.idType) && name.equals(beer.name) && Objects.equals(tagline, beer.tagline) && Objects.equals(firstBrewed, beer.firstBrewed) && Objects.equals(description, beer.description) && Objects.equals(imageUrl, beer.imageUrl) && Objects.equals(foodPairing, foodPairing);
    }


    @Override
    public int hashCode() {
        return Objects.hash(externalId, idType, name, tagline, firstBrewed, description, imageUrl, ibu, foodPairing);
    }

}
