package com.yp.service;

import com.yp.converter.UserConverter;
import com.yp.dto.UserDto;
import com.yp.entity.User;
import com.yp.repos.AuthorityRepository;
import com.yp.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private AuthorityService authorityService;

    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> {
            UserDto userDto = UserConverter.converToUserDto(user);
            userDtos.add(userDto);
        });

        return userDtos;
    }

    public UserDto getUser(String username){
        User user=  userRepository.findById(username).get();
        UserDto userDto = UserConverter.converToUserDto(user);
        return userDto;
    }

    public User addUser(UserDto user){
        User newUser = UserConverter.converToUser(user);
        return userRepository.save(newUser);
    }

    public User updateUser(String username, UserDto user){
        User newUser = UserConverter.converToUser(user);
        User oldUser = userRepository.findById(username).get();
        oldUser.setUserName(newUser.getUserName());
        oldUser.setPassWord(newUser.getPassWord());
        oldUser.setAuthorities(newUser.getAuthorities());
        oldUser.setEnabled(newUser.isEnabled());
        return userRepository.save(oldUser);
    }

    public void deleteUser(String username){

        userRepository.deleteById(username);

    }

}
