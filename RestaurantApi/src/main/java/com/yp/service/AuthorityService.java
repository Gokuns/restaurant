package com.yp.service;


import com.yp.dto.AuthorityDto;
import com.yp.dto.UserDto;
import com.yp.entity.Authority;
import com.yp.exception.BusinessRuleException;
import com.yp.exception.ContentNotFoundException;
import com.yp.mapper.AuthorityMapper;
import com.yp.mapper.UserMapper;
import com.yp.repos.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageSource messageSource;


    private static final String BUSINESS_RULE_EXCEPTION = "BusinessRuleException";
    private static final String CONTENT_NOT_FOUND = "ContentNotFound";


    public AuthorityDto getRole(Long id, String lang){
        Optional<Authority> optionalAuthority= authorityRepository.findById(id);
        if(optionalAuthority.isEmpty()){
            throw new ContentNotFoundException(messageSource.getMessage(CONTENT_NOT_FOUND, new Object[0], new Locale(lang)));
        }
        return authorityMapper.toDto(optionalAuthority.get());
    }

    public List<AuthorityDto> getAllRoles(){
        return authorityRepository.findAll().stream().map(authorityMapper::toDto).collect(Collectors.toList());
    }
    public Authority addRole(AuthorityDto authority, String lang){
        if(authority==null) throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        Authority auth = authorityMapper.toEntity(authority);
        return authorityRepository.save(auth);
    }
    public Authority editRole(AuthorityDto authority, Long id, String lang){
        Optional<Authority> optionalAuthority = authorityRepository.findById(id);
        if (optionalAuthority.isEmpty()){
            throw new ContentNotFoundException(messageSource.getMessage(CONTENT_NOT_FOUND, new Object[0], new Locale(lang)));
        }
        if(authority==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        Authority auth = optionalAuthority.get();
        if(!auth.getRole().equals(authority.getRole())){
            auth.setRole(authority.getRole());
        }
        return authorityRepository.save(auth);
    }
    public void deleterRole(Long id, String lang){
        if(id==null){
            throw new BusinessRuleException(messageSource.getMessage(BUSINESS_RULE_EXCEPTION, new Object[0], new Locale(lang)));
        }
        authorityRepository.deleteById(id);
    }


}
