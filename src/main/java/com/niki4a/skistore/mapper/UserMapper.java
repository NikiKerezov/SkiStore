package com.niki4a.skistore.mapper;

import com.niki4a.skistore.controller.resources.UserResource;
import com.niki4a.skistore.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper (uses = {OrderMapper.class})
public interface UserMapper {
    UserMapper USER_MAPPER = org.mapstruct.factory.Mappers.getMapper(UserMapper.class);

    UserResource toUserResource(User user);
    User fromUserResource(UserResource userResource);
    List<UserResource> toUserResourceList(List<User> usersList);
    List<User> fromUserResourceList(List<UserResource> userList);
}
