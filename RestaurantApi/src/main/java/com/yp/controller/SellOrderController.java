package com.yp.controller;

import com.yp.model.SellOrder;
import com.yp.service.SellOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class SellOrderController {
    @Autowired
    private SellOrderService sellOrderService;

    @PostMapping("/checkout/add")
    public void checkOut2(@RequestBody List<SellOrder> sellOrders){
        sellOrderService.addOrderLst(sellOrders);
    }

}
