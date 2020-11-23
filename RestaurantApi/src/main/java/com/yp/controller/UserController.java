package com.yp.controller;

import com.google.gson.Gson;
import com.yp.model.User;
import com.yp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String getAllUsers(){
        return new Gson().toJson(userService.getAllUsers());
    }

    @GetMapping("/{username}")
    public String getUser(@PathVariable(value = "username")String username){
        return new Gson().toJson(userService.getUser(username));
    }

    @PostMapping("/add")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @PutMapping("/{username}/put")
    public void putUser(@PathVariable(value = "username")String username, @RequestBody User user){
        userService.updateUser(username, user);
    }

    @DeleteMapping("/{username}/delete")
    public void deleteUser(@PathVariable(value = "username")String username){
        userService.deleteUser(username);
    }


}
