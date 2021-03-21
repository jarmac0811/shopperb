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
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "admin", target = "admin"),
    })
    UserDTO map(User entity);

    @Mappings({
            @Mapping(source = "userId", target = "userId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "admin", target = "admin"),
    })
    User map(UserDTO dto);


}
