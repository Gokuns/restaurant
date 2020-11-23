package com.yp.service;

import com.yp.model.User;
import com.yp.repos.AuthorityRepository;
import com.yp.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityService authorityService;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(String username){
        return userRepository.findById(username).get();
    }

    public void addUser(User user){
        String pw = "{noop}" +user.getPassWord();
        user.setPassWord(pw);
        userRepository.save(user);
    }

    public void updateUser(String username, User user){
        deleteUser(username);
        userRepository.save(user);
    }

    public void deleteUser(String username){
        userRepository.deleteById(username);
    }

}
