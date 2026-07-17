package com.project.controller;

import com.project.dto.UserRequest;
import com.project.dto.UserResponse;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.fetchAllUsers());

    }
    @GetMapping("/api/users/{id}")
    public ResponseEntity<Optional<UserResponse>> getAllUsers(@PathVariable long id) {
        Optional<UserResponse> user=userService.getUser(id);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @PostMapping("/api/users")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        userService.addUser(userRequest);
        return ResponseEntity.ok("User Added Successfully");
    }
    @PutMapping("/api/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable long id, @RequestBody UserRequest userRequest){
        boolean update = userService.updateUser(id, userRequest);
        if(update){
            return ResponseEntity.ok("User Updated Successfully");
        }
        else return ResponseEntity.notFound().build();
    }
}