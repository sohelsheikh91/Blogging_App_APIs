package com.springboot.blog.controllers;

import com.springboot.blog.payloads.ApiResponse;
import com.springboot.blog.payloads.UserDto;
import com.springboot.blog.services.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Post - create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto user = this.userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    //PUT - update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer Id){
        UserDto updatedUser = this.userService.updateUser(userDto, Id);
        return ResponseEntity.ok(updatedUser);
    }
    //DELETE - delete user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer Id){
        this.userService.deleteUser(Id);
        //return new ResponseEntity<>(Map.of("Message", "User Deleted Successfully"), HttpStatus.ACCEPTED);
        return  new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true), HttpStatus.OK);
    }
    //GET - get user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUsers(@PathVariable("userId") Integer Id){
        return ResponseEntity.ok(this.userService.getUserById(Id));
    }
}
