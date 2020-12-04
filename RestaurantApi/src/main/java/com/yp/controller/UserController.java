package com.yp.controller;

import com.google.gson.Gson;
import com.yp.dto.UserDto;
import com.yp.entity.User;
import com.yp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public UserDto getUser(@PathVariable(value = "username")String username){
        return userService.getUser(username);
    }

    @PostMapping("/add")
    public void addUser(@RequestBody UserDto user){
        userService.addUser(user);
    }

    @PutMapping("/{username}/put")
    public void putUser(@PathVariable(value = "username")String username, @RequestBody UserDto user){
        userService.updateUser(username, user);
    }

    @DeleteMapping("/{username}/delete")
    public void deleteUser(@PathVariable(value = "username")String username){
        userService.deleteUser(username);
    }


}
