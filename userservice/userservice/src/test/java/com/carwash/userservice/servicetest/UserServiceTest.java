package com.carwash.userservice.servicetest;

import com.carwash.userservice.model.User;
import com.carwash.userservice.model.UserDto;
import com.carwash.userservice.repository.UserRepository;
import com.carwash.userservice.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.carwash.userservice.model.Role.WASHER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {


    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;


    @Test
    void testGetAllUsers(){
        userService.getAllUsers();
        verify(userRepository).findAll();
    }

    @Test
    void testGetUsers(){
        when(userRepository.findAll()).thenReturn(Stream
           .of(new User(100,"max", "maxwell","9538836988","chetan@g.com", "helloo12",  WASHER,true),
             new User(101,"maxi", "maxwell",  "9538836987", "chetan@g.com", "helloo12",  WASHER,true)).collect(Collectors.toList()));
        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    void testAddUser() {
        UserDto userDto = new UserDto("max", "maxwell", "9538836988", "chetan@gmail.com", "hello123");
        User user = new User();
        user.setUserName("max");
        user.setPhoneNo("9538836987");
        user.setFullName("maxwell");
        user.setEmail("chetan@gmail.com");
        user.setPassword("hello123");
        user.setIsActive(true);

        when(userRepository.save(user)).thenReturn(user);
        User userInfo = userService.createUser(userDto);

        assertEquals(user.getUserName(), userInfo.getUserName());
        assertEquals(user.getEmail(), userInfo.getEmail());
        assertNotEquals(user.getPhoneNo(), userInfo.getPhoneNo());
    }
}