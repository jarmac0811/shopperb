package com.recruitment.beerRestApiTask.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private String userId;
    private String externalUserId;
    private String userName;
//    private String password;
    private String email;
    private String roles;
    private String permissions;
    private Boolean admin;

    public UserDTO(AuthViewModel requestAuth) {
        this.externalUserId = requestAuth.getUserid();
        this.userName = requestAuth.getUsername();
        this.email = requestAuth.getEmail();
    }
}
