package com.yp.service;


import com.yp.dto.AuthorityDto;
import com.yp.dto.UserDto;
import com.yp.entity.Authority;
import com.yp.mapper.AuthorityMapper;
import com.yp.repos.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AuthorityMapper authorityMapper;

    public AuthorityDto getRole(Long id){
        Optional<Authority> optionalAuthority= authorityRepository.findById(id);
        if(optionalAuthority.isPresent()){
            Authority authority=  optionalAuthority.get();
            return authorityMapper.toDto(authority);
        }else return  null;
    }
    public List<AuthorityDto> getAllRoles(){
        List<AuthorityDto>  authorityDtos = new ArrayList<>();
        authorityRepository.findAll().iterator().forEachRemaining(authority ->
                authorityDtos.add(authorityMapper.toDto(authority)));
        return authorityDtos;
    }
    public Authority addRole(AuthorityDto authority){
        Authority auth = authorityMapper.toEntity(authority);
        return authorityRepository.save(auth);
    }
    public Authority editRole(AuthorityDto authority, Long id){
        Optional<Authority> optionalAuthority = authorityRepository.findById(id);
        if (optionalAuthority.isPresent()){
            Authority auth = optionalAuthority.get();
            auth.setAuthority(authority.getAuthority());
            return authorityRepository.saveAndFlush(auth);
        }
        return null;
    }
    public void deleterRole(Long id){
        authorityRepository.deleteById(id);
    }

    public Set<UserDto> getUsersWithRole(Long id){
        Optional<Authority> optionalAuthority = authorityRepository.findById(id);
        Set<UserDto> userDtos = new HashSet<>();
        if (optionalAuthority.isPresent()){
            Authority authority = optionalAuthority.get();
        }

        return userDtos;
    }
}
