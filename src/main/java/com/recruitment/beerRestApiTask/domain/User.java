package com.recruitment.beerRestApiTask.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long userId;
    private String externalUserId;
    private String userName;
    private String password;
    private String roles;
    private String permissions;
    private String email;
    private Boolean admin;

    public List<String> getRolesList() {
        if (roles != null && this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        } else {
            return new ArrayList<>();
        }
    }

    public List<String> getPermissionsList() {
        if (permissions != null && this.permissions.length() > 0) {
            return Arrays.asList(this.permissions.split(","));
        } else {
            return new ArrayList<>();
        }
    }
}
