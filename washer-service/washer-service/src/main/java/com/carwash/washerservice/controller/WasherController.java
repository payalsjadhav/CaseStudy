package com.carwash.washerservice.controller;

import com.carwash.washerservice.model.Washer;
import com.carwash.washerservice.model.WasherDto;
import com.carwash.washerservice.model.WasherUpdate;
import com.carwash.washerservice.service.WasherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/washers")
public class WasherController {

    @Autowired
    private WasherServiceImpl washerService;

    @PostMapping("/washer/signup")
    public ResponseEntity<String> createCustomer(@RequestBody Washer washer){
        washerService.createWasher(washer);
        return new ResponseEntity<>("registered successfully", HttpStatus.OK);
    }

    @GetMapping("/washer/{userName}")
    public ResponseEntity<WasherDto>getUserByUserName(@PathVariable String userName){
        WasherDto user= washerService.gatWasherByUserName(userName);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/allwashers")
    public ResponseEntity<List<WasherDto>> getAllUsers(){
        List<WasherDto> users = washerService.getAllWashers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }


    @PutMapping("/washer/update")
    public ResponseEntity<String> updateUser(@RequestBody WasherUpdate washer){
        washerService.updateUser(washer);
        return new ResponseEntity<>("account updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/washer/{userName}")
    public ResponseEntity<String> deleteUser(@PathVariable String userName){
        washerService.deleteUser(userName);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }


}
