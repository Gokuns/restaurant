package com.yp.controller;

import com.yp.dto.UserDto;
import com.yp.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "User Controller")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public UserDto getUser(@PathVariable(value = "id")Long id, @RequestParam(value = "lang", defaultValue = "en") String lang){
        return userService.getUser(id, lang);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addUser(@Valid  @RequestBody UserDto user, @RequestParam(value = "lang", defaultValue = "en") String lang){
        userService.addUser(user, lang);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void putUser(@PathVariable(value = "id")Long id,@Valid @RequestBody UserDto user, @RequestParam(value = "lang", defaultValue = "en") String lang){
        userService.updateUser(id, user, lang);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(@PathVariable(value = "id")Long id, @RequestParam(value = "lang", defaultValue = "en") String lang){
        userService.deleteUser(id, lang);
    }


}
