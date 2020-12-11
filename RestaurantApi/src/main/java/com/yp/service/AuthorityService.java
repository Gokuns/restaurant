package com.yp.service;

import com.yp.converter.AuthorityConverter;
import com.yp.converter.UserConverter;
import com.yp.dto.AuthorityDto;
import com.yp.dto.UserDto;
import com.yp.entity.Authority;
import com.yp.entity.User;
import com.yp.repos.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;

    public AuthorityDto getRole(String id){
        Optional<Authority> optionalAuthority= authorityRepository.findById(id);
        if(optionalAuthority.isPresent()){
            Authority authority=  optionalAuthority.get();
            return AuthorityConverter.converToAuthorityDto(authority);
        }else return  null;
    }
    public List<AuthorityDto> getAllRoles(){
        List<Authority> auths = authorityRepository.findAll();
        List<AuthorityDto>  authorityDtos = new ArrayList<>();
        auths.forEach(authority ->
            authorityDtos.add(AuthorityConverter.converToAuthorityDto(authority)));
        return authorityDtos;
    }
    public Authority addRole(AuthorityDto authority){
        Authority auth = AuthorityConverter.converToAuthority(authority);
        return authorityRepository.save(auth);
    }
    public Authority editRole(AuthorityDto authority, String id){
        Authority a = new Authority();
        Optional<Authority> optionalAuthority = authorityRepository.findById(id);
        if (optionalAuthority.isPresent()){
            Authority auth = optionalAuthority.get();
            a.setUsers(auth.getUsers());
            a.setAuthority(authority.getAuthority());
        }
        return authorityRepository.saveAndFlush(a);
    }
    public void deleterRole(String id){
        authorityRepository.deleteById(id);
    }

    public Set<UserDto> getUsersWithRole(String id){
        Optional<Authority> optionalAuthority = authorityRepository.findById(id);
        Set<UserDto> userDtos = new HashSet<>();
        if (optionalAuthority.isPresent()){
            Authority authority = optionalAuthority.get();
            Set<User> users = authority.getUsers();

            users.forEach(user ->
                    userDtos.add(UserConverter.converToUserDto(user)));
        }

        return userDtos;
    }
}
