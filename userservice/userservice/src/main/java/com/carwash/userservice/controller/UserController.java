package com.carwash.userservice.controller;

import com.carwash.userservice.model.*;
import com.carwash.userservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/user/signup")
    public ResponseEntity<String> createCustomer(@RequestBody UserDto user){
        userService.createUser(user);
        return new ResponseEntity<>("registered successfully", HttpStatus.OK);
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity <UserDtos>getUserByUserName(@PathVariable String userName){
         UserDtos user= userService.gatUserByUserName(userName);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/allusers")
    public ResponseEntity<List<UserDtos>> getAllUsers(){
     List<UserDtos> users = userService.getAllUsers();
     return new ResponseEntity<>(users,HttpStatus.OK);
    }



    @PutMapping("/user/update")
    public ResponseEntity<String> updateUser(@RequestBody UserUpdate userDto){
        userService.updateUser(userDto);
        return new ResponseEntity<>("account updated successfully", HttpStatus.OK);
    }

   @DeleteMapping("/user/{userName}")
    public ResponseEntity<String> deleteUser(@PathVariable String userName){
        userService.deleteUser(userName);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
   }

   @PostMapping("/user/admin-signup")
   public ResponseEntity<String> createAdmin(@RequestBody UserDto userDto){
       String username=userService.createAdmin(userDto);
       return new ResponseEntity<>("admin account created with id "+username,HttpStatus.OK);
   }

   @GetMapping("/{userName}")
    public  ResponseEntity<LoginResponse> loginResponse(@PathVariable String userName){
        LoginResponse response=userService.userLoginResponse(userName);
        return new ResponseEntity<>(response,HttpStatus.OK) ;
   }

   @GetMapping("/exist/{userName}")
    public ResponseEntity<Boolean> checkUserExist(@PathVariable("userName") String userName){
        Boolean user=userService.userExistByUserName(userName);
        return new ResponseEntity<Boolean>(user,HttpStatus.OK);
   }
}
