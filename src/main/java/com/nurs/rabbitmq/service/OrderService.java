package com.nurs.rabbitmq.service;


import com.nurs.rabbitmq.config.MQConfig;
import com.nurs.rabbitmq.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final RabbitTemplate template;

    public void createOrder(BigDecimal amount) {
        Order order = new Order(LocalDateTime.now(), amount, false);
        template.convertAndSend(MQConfig.EXCHANGE,
                               MQConfig.ROUTING_KEY, order);
    }

//    public Order getOrder(Long orderId) {
//        return orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
//    }
//
//    public Payment pay(Long orderId, String creditCardNumber) {
//        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
//
//
//
//        orderRepository.save(order.markPaid());
//        return paymentRepository.save(new Payment(order, creditCardNumber));
//    }
}