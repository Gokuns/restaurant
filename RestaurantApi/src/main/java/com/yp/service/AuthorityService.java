package com.yp.service;

import com.yp.model.Authority;
import com.yp.repos.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;

    public String getRoleById(int id){
        Authority authority = authorityRepository.findById(id).get();
        return  authority.getAuthority();
    }
    public List<Authority> getAuthoritiesByName(String name){
        List<Authority> lst = authorityRepository.findAll();
        List<Authority> laist = lst.stream().filter(c -> c.getUsername().equals(name)).collect(Collectors.toList());
        return laist;
    }
    public String getAuthStringByName(String name){
        List<Authority> lst =  getAuthoritiesByName(name);
        return lst.toString();
    }
}
