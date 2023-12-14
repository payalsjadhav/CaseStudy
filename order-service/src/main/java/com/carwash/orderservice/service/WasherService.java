package com.carwash.orderservice.service;

import com.carwash.orderservice.model.WasherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WasherService {

    @Autowired
    RestTemplate restTemplate;


    public WasherDto getWasher(String washerName){
        WasherDto washer=restTemplate.getForObject("http://localhost:8085/washers/washer/"+washerName, WasherDto.class);

        return washer;
    }

}
