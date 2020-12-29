package com.yp.controller;

import com.yp.dto.AuthorityDto;
import com.yp.service.AuthorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Authority Controller")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/roles")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @ApiOperation(value = "List roles", notes = "Lists through all the roles.")
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public List<AuthorityDto> getAllRoles(){
        return authorityService.getAllRoles();
    }

    @ApiOperation(value = "Get Role", notes = "Gets the role with the given id")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public AuthorityDto getRole(@PathVariable(name = "id") Long id, @RequestParam(value = "lang", defaultValue = "en") String lang){
        return authorityService.getRole(id, lang);
    }


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addRole(@Valid @RequestBody AuthorityDto authority, @RequestParam(value = "lang", defaultValue = "en") String lang){
        authorityService.addRole(authority, lang);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void editRole(@Valid @RequestBody AuthorityDto authority, @PathVariable(name = "id") Long id, @RequestParam(value = "lang", defaultValue = "en") String lang){
        authorityService.editRole(authority,id, lang);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteRole( @PathVariable(name = "id") Long id, @RequestParam(value = "lang", defaultValue = "en") String lang){
        authorityService.deleterRole(id, lang);
    }

}
