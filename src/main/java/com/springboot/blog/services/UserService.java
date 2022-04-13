package com.springboot.blog.services;

import com.springboot.blog.entities.User;
import com.springboot.blog.payloads.UserDto;

import java.util.List;

public interface UserService {    //interfae banaya

    //User createUser(User user);
    UserDto createUser(UserDto user);             //We will not expose fields of User entity, entity can have more dynamic field
                                                //UserDto will have only fields which are receiving from user end
                                                //for data transfer, will use User Dao
    UserDto updateUser(UserDto user, Integer userId);  //userid details
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);


}
