package com.yp.service;

import com.yp.dto.UserDto;
import com.yp.entity.Authority;
import com.yp.entity.User;
import com.yp.exception.BusinessRuleException;
import com.yp.exception.ContentNotFoundException;
import com.yp.mapper.UserMapper;
import com.yp.repos.AuthorityRepository;
import com.yp.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private BCryptPasswordEncoder encoder;
    private static final String BUSINESS_RULE_EXCEPTION = "BusinessRuleException";
    private static final String CONTENT_NOT_FOUND = "ContentNotFound";


    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    public UserDto getUser(Long id, String lang){
        if(id==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new ContentNotFoundException(messageSource.getMessage(CONTENT_NOT_FOUND, new Object[0], new Locale(lang)));
        }
         return userMapper.toDto(optionalUser.get());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public User addUser(UserDto user, String lang){
        if(user==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        User newUser = userMapper.toEntity(user);
        newUser.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public User updateUser(Long id, UserDto user, String lang){
        if(id==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        if(user==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));

        }
        User newUser = userMapper.toEntity(user);
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new ContentNotFoundException(messageSource.getMessage(CONTENT_NOT_FOUND, new Object[0], new Locale(lang)));
        }
        User oldUser = optionalUser.get();
        if(!oldUser.getUsername().equals(newUser.getUsername())){
            oldUser.setUsername(newUser.getUsername());
        }
        if(!encoder.matches(newUser.getPassword(), oldUser.getPassword())){
            oldUser.setPassword(newUser.getPassword());
        }
        if(!oldUser.getAuthorities().equals(newUser.getAuthorities())){
            oldUser.setAuthorities(newUser.getAuthorities());
        }
        if(!oldUser.isEnabled()== newUser.isEnabled()){
            oldUser.setEnabled(newUser.isEnabled());
        }
        return userRepository.save(oldUser);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(Long id, String lang){
        if(id==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        userRepository.deleteById(id);
    }

    public Set<UserDto> getUsersWithRole(Long id, String lang){
        Optional<Authority> optionalAuthority = authorityRepository.findById(id);
        if (optionalAuthority.isEmpty()) {
            throw new ContentNotFoundException(messageSource.getMessage(CONTENT_NOT_FOUND, new Object[0], new Locale(lang)));
        }
        Authority authority = optionalAuthority.get();
        return userRepository.findAllByAuthorities(authority).stream().map(userMapper::toDto).collect(Collectors.toSet());
    }

}
