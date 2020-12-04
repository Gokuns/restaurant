package com.yp.controller;

import com.yp.dto.SellOrderDto;
import com.yp.entity.SellOrder;
import com.yp.service.SellOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
public class SellOrderController {
    @Autowired
    private SellOrderService sellOrderService;

    @PostMapping("/checkout/add")
    public void checkOut2(@RequestBody List<SellOrderDto> sellOrders){
        sellOrderService.addOrderLst(sellOrders);
    }

    @GetMapping("/list")
    public void getAllOrders(){

    }

}
