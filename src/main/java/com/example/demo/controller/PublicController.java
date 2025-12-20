package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")

public class PublicController {


    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "success";
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        userService.saveEntry(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
