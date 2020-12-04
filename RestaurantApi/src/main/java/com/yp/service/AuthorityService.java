package com.yp.service;

import com.yp.converter.AuthorityConverter;
import com.yp.dto.AuthorityDto;
import com.yp.entity.Authority;
import com.yp.entity.User;
import com.yp.repos.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;

    public AuthorityDto getRole(String id){
        Optional<Authority> optionalAuthority= authorityRepository.findById(id);
        if(optionalAuthority.isPresent()){
            Authority authority=  optionalAuthority.get();
            AuthorityDto auth =AuthorityConverter.converToAuthorityDto(authority);
            return auth;

        }else return  null;
    }
    public List<AuthorityDto> getAllRoles(){
        List<Authority> auths = authorityRepository.findAll();
        List<AuthorityDto>  authorityDtos = new ArrayList<>();
        auths.forEach(authority -> {
            authorityDtos.add(AuthorityConverter.converToAuthorityDto(authority));
        });
        return authorityDtos;
    }

    public Authority addRole(AuthorityDto authority){
        Authority auth = AuthorityConverter.converToAuthority(authority);
        return authorityRepository.save(auth);

    }

    public Authority editRole(AuthorityDto authority, String id){
        Authority a = new Authority();
        Authority auth = authorityRepository.findById(id).get();
        a.setUsers(auth.getUsers());
        a.setAuthority(authority.getAuthority());
        authorityRepository.deleteById(id);
        return authorityRepository.saveAndFlush(a);
    }
    public void deleterRole(String id){
        authorityRepository.deleteById(id);
    }

    public Set<User> getUsersWithRole(String id){
        Authority authority = authorityRepository.findById(id).get();
        return authority.getUsers();
    }

}
