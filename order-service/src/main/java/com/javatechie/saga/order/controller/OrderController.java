package com.javatechie.saga.order.controller;

import com.javatechie.commondtos.dto.OrderRequestDto;
import com.javatechie.saga.order.entity.PurchaseOrder;
import com.javatechie.saga.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public PurchaseOrder createOrder(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.createOrder(orderRequestDto);
    }

    @GetMapping
    public List<PurchaseOrder> getOrders(){
       return orderService.getAllOrders();
    }
}
