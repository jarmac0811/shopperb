package com.recruitment.beerRestApiTask.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthViewModel {
//    @JsonProperty("userid")
    private String userid;
//    @JsonProperty("username")
    private String username;
    private String email;
    private String password;
}
