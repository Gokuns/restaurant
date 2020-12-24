package com.yp.controller;

import com.google.gson.Gson;
import com.yp.dto.UserDto;
import com.yp.entity.User;
import com.yp.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "User Controller")
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

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable(value = "id")Long id){
        return userService.getUser(id);
    }

    @PostMapping("/add")
    public void addUser(@RequestBody UserDto user){
        userService.addUser(user);
    }

    @PutMapping("/{id}/put")
    public void putUser(@PathVariable(value = "id")Long id, @RequestBody UserDto user){
        userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteUser(@PathVariable(value = "id")Long id){
        userService.deleteUser(id);
    }


}
