package com.yp.service;

import com.yp.converter.AuthorityConverter;
import com.yp.dto.AuthorityDto;
import com.yp.dto.UserDto;
import com.yp.entity.Authority;
import com.yp.repos.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;

    public AuthorityDto getRole(int id){
        Optional<Authority> optionalAuthority= authorityRepository.findById(id);
        if(optionalAuthority.isPresent()){
            Authority authority=  optionalAuthority.get();
            return AuthorityConverter.converToAuthorityDto(authority);
        }else return  null;
    }
    public List<AuthorityDto> getAllRoles(){
        List<AuthorityDto>  authorityDtos = new ArrayList<>();
        authorityRepository.findAll().iterator().forEachRemaining(authority ->
                authorityDtos.add(AuthorityConverter.converToAuthorityDto(authority)));
        return authorityDtos;
    }
    public Authority addRole(AuthorityDto authority){
        Authority auth = AuthorityConverter.converToAuthority(authority);
        return authorityRepository.save(auth);
    }
    public Authority editRole(AuthorityDto authority, int id){
        Optional<Authority> optionalAuthority = authorityRepository.findById(id);
        if (optionalAuthority.isPresent()){
            Authority auth = optionalAuthority.get();
            auth.setAuthority(authority.getAuthority());
            return authorityRepository.saveAndFlush(auth);
        }
        return null;
    }
    public void deleterRole(int id){
        authorityRepository.deleteById(id);
    }

    public Set<UserDto> getUsersWithRole(int id){
        Optional<Authority> optionalAuthority = authorityRepository.findById(id);
        Set<UserDto> userDtos = new HashSet<>();
        if (optionalAuthority.isPresent()){
            Authority authority = optionalAuthority.get();
        }

        return userDtos;
    }
}
