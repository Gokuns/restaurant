package com.yp.service;

import com.yp.model.User;
import com.yp.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(int id){
        return userRepository.findById(id).get();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(int id, User user){
        deleteUser(id);
        userRepository.save(user);
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

}
