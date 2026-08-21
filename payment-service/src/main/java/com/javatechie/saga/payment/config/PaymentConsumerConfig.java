package com.javatechie.saga.payment.config;


import com.javatechie.commondtos.event.OrderEvent;
import com.javatechie.commondtos.event.OrderStatus;
import com.javatechie.commondtos.event.PaymentEvent;
import com.javatechie.saga.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class PaymentConsumerConfig {


    @Autowired
    private PaymentService paymentService;

    @Bean
    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor(){
        return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
    }

    private Mono<PaymentEvent> processPayment(OrderEvent orderEvent){

        //get the user id
        //check the balance availability
        //if balance sufficient -> payment COMPLETED and deduct amount from db
        //if balance not sufficient -> cancel order event and update the amount in db

        if (OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())){
            return Mono.fromSupplier(()->this.paymentService.newOrderEvent(orderEvent));
        }else {
            return Mono.fromRunnable(()->this.paymentService.cancelOrderEvent(orderEvent));
        }
    }
}
