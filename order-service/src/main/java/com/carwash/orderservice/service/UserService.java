package com.carwash.orderservice.service;

import com.carwash.orderservice.model.UserDtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

     public UserDtos getUser(String userName){

    UserDtos userDtos =restTemplate.getForObject("http://localhost:8081/users/user/"+userName,UserDtos.class);
         return  userDtos;
     }


}
