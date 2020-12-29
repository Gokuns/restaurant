package com.yp.controller;

import com.yp.dto.WaiterDto;
import com.yp.service.WaiterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Waiter Controller")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/waiters")
public class WaiterController {
    @Autowired
    private WaiterService waiterService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public List<WaiterDto> getAllUsers(){
        return waiterService.getAllWaiters();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public WaiterDto getUser(@PathVariable(value = "id")Long id,
                             @RequestParam(value = "lang", defaultValue = "en") String lang){
        return waiterService.getWaiter(id, lang);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addUser(@Valid  @RequestBody WaiterDto waiterDto,
                        @RequestParam(value = "lang", defaultValue = "en") String lang){
        waiterService.addWaiter(waiterDto, lang);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void putUser(@PathVariable(value = "id")Long id,@Valid @RequestBody WaiterDto waiterDto,
                        @RequestParam(value = "lang", defaultValue = "en") String lang){
        waiterService.updateWaiter(id, waiterDto, lang);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(@PathVariable(value = "id")Long id,
                           @RequestParam(value = "lang", defaultValue = "en") String lang){
        waiterService.deleteWaiter(id, lang);
    }



}
