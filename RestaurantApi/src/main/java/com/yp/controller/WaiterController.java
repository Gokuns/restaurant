package com.yp.controller;

import com.yp.dto.UserDto;
import com.yp.dto.WaiterDto;
import com.yp.repos.WaiterRepository;
import com.yp.service.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/waiter")
public class WaiterController {
    @Autowired
    private WaiterService waiterService;

    @GetMapping("/list")
    public List<WaiterDto> getAllUsers(){
        return waiterService.getAllWaiters();
    }

    @GetMapping("/{id}")
    public WaiterDto getUser(@PathVariable(value = "id")Long id){
        return waiterService.getWaiter(id);
    }

    @PostMapping("/add")
    public void addUser(@RequestBody WaiterDto waiterDto){
        waiterService.addWaiter(waiterDto);
    }

    @PutMapping("/{id}/put")
    public void putUser(@PathVariable(value = "id")Long id, @RequestBody WaiterDto waiterDto){
        waiterService.updateWaiter(id, waiterDto);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteUser(@PathVariable(value = "id")Long id){
        waiterService.deleteWaiter(id);
    }



}
