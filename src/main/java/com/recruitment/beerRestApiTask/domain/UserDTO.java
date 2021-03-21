package com.recruitment.beerRestApiTask.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDTO {

    private String userId;
    private String name;
    private String email;
    private Boolean admin;

}
