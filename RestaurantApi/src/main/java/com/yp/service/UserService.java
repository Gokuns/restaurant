package com.yp.service;

import com.yp.dto.UserDto;
import com.yp.entity.User;
import com.yp.mapper.AuthorityMapper;
import com.yp.mapper.UserMapper;
import com.yp.repos.AuthorityRepository;
import com.yp.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private UserMapper userMapper;


    public List<UserDto> getAllUsers(){
        List<UserDto> userDtos = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(user -> userDtos.add(userMapper.toDto(user)));
        return userDtos;
    }

    public UserDto getUser(Long id){
        User user=  userRepository.findById(id).get();
        UserDto userDto = userMapper.toDto(user);
        return userDto;
    }

    public User addUser(UserDto user){
        User newUser = userMapper.toEntity(user);
        return userRepository.save(newUser);
    }

    public User updateUser(Long id, UserDto user){
        User newUser = userMapper.toEntity(user);
        User oldUser = userRepository.findById(id).get();
        oldUser.setUsername(newUser.getUsername());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setAuthorities(newUser.getAuthorities());
        oldUser.setEnabled(newUser.isEnabled());
        return userRepository.save(oldUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);

    }

}
