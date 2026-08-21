package com.javatechie.saga.order.service;

import com.javatechie.commondtos.dto.OrderRequestDto;
import com.javatechie.commondtos.event.OrderEvent;
import com.javatechie.commondtos.event.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {

//    @Autowired
//    private Sinks.Many<OrderEvent> orderSinks;
//
//    public void publishOrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus){
//        OrderEvent orderEvent = new OrderEvent(orderRequestDto, orderStatus);
//        System.out.println("Publishing OrderEvent: " + orderEvent);
//
//        Sinks.EmitResult result =
//                orderSinks.tryEmitNext(orderEvent);
//
//        System.out.println("Sinks.EmitResult: " + result);
//    }


    private final StreamBridge streamBridge;

    public OrderStatusPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void publishOrderEvent(OrderRequestDto orderRequestDto,
                                  OrderStatus orderStatus) {

        OrderEvent orderEvent =
                new OrderEvent(orderRequestDto, orderStatus);

        boolean result =
                streamBridge.send("orderEvent-out-0", orderEvent);

        System.out.println("=================================");
        System.out.println("ORDER EVENT = " + orderEvent);
        System.out.println("KAFKA SEND RESULT = " + result);
        System.out.println("=================================");
    }
}
