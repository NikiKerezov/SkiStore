package com.niki4a.skistore.mapper;

import com.niki4a.skistore.controller.resources.UserResource;
import com.niki4a.skistore.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    UserResource toUserResource(User user);
    User fromUserResource(UserResource userResource);

    List<UserResource> toUserResourceList(List<User> userList);
    List<User> fromUserResourceList(List<UserResource> userList);
}

