package com.yp.controller;

import com.yp.dto.AuthorityDto;
import com.yp.entity.Authority;
import com.yp.service.AuthorityService;
import com.yp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/role")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @GetMapping("/list")
    public List<AuthorityDto> getAllRoles(){
        return authorityService.getAllRoles();
    }

    @GetMapping("/{id}")
    public AuthorityDto getRole(@PathVariable(name = "id") int id){
        return authorityService.getRole(id);
    }

    @PostMapping("/add")
    public void addRole(@RequestBody AuthorityDto authority){
        authorityService.addRole(authority);
    }

    @PutMapping("/{id}/put")
    public void editRole(@RequestBody AuthorityDto authority, @PathVariable(name = "id") int id){
        authorityService.editRole(authority,id);
    }
    @DeleteMapping("{id}/delete")
    public void deleteRole(@PathVariable(name = "id") int id){
        authorityService.deleterRole(id);
    }

}
