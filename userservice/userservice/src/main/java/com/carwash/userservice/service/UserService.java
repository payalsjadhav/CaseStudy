package com.carwash.userservice.service;

import com.carwash.userservice.model.*;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(UserDto userDto);

    UserDtos gatUserByUserName(String userName);

    List<UserDtos> getAllUsers();

    String updateUser(UserUpdate userDto);

    void deleteUser(String userName);

    String createAdmin(UserDto userDto);

    LoginResponse userLoginResponse(String userName);

    Boolean userExistByUserName(String userName);
}
