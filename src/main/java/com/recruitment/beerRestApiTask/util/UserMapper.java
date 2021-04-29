package com.recruitment.beerRestApiTask.util;

import com.recruitment.beerRestApiTask.domain.User;
import com.recruitment.beerRestApiTask.domain.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(source = "userId", target = "userId"),
            @Mapping(source = "externalUserId", target = "externalUserId"),
            @Mapping(source = "userName", target = "userName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "roles", target = "roles"),
            @Mapping(source = "permissions", target = "permissions"),
            @Mapping(source = "admin", target = "admin"),
    })
    UserDTO map(User entity);

    @Mappings({
            @Mapping(source = "userId", target = "userId"),
            @Mapping(source = "externalUserId", target = "externalUserId"),
            @Mapping(source = "userName", target = "userName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "roles", target = "roles"),
            @Mapping(source = "permissions", target = "permissions"),
            @Mapping(source = "admin", target = "admin"),
    })
    User map(UserDTO dto);


}
