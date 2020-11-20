package com.yp.service;

import com.yp.model.Product;
import com.yp.model.SellOrder;
import com.yp.repos.ProductRepository;
import com.yp.repos.SellOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellOrderService {
    @Autowired
    private SellOrderRepository sellOrderRepository;

    public void addOrderLst(List<SellOrder> sellOrders) {
        sellOrderRepository.saveAll(sellOrders);
    }
}
