package com.yp.controller;

import com.yp.dto.SellOrderDto;
import com.yp.service.SellOrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "SellOrder Controller")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/orders")
public class SellOrderController {
    @Autowired
    private SellOrderService sellOrderService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public void addOrder(@RequestBody List<SellOrderDto> sellOrders, @RequestParam(value = "lang", defaultValue = "en") String lang){
        sellOrderService.addOrderLst(sellOrders, lang);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<SellOrderDto> getAllOrders(){
        return sellOrderService.getAllOrders();
    }

}
