package com.example.zellowapplication.service;

import com.example.zellowapplication.dto.UserDto;
import com.example.zellowapplication.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}