package com.yp.controller;

import com.google.gson.Gson;
import com.yp.model.User;
import com.yp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/list")
    public String getAllUsers(){
        return new Gson().toJson(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable(value = "id")int id){
        return new Gson().toJson(userService.getUser(id));
    }

    @PostMapping("/user/add")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @PutMapping("/user/{id}")
    public void putUser(@PathVariable(value = "id")int id, @RequestBody User user){
        userService.updateUser(id, user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable(value = "id")int id){
        userService.deleteUser(id);
    }


}
