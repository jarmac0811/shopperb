package com.recruitment.beerRestApiTask.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class User {
    @Id
    private String userId;
    private String name;
    private String email;
    private Boolean Admin;
}
