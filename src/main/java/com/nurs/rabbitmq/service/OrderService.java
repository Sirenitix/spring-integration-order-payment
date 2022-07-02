package com.nurs.rabbitmq.service;


import com.nurs.rabbitmq.config.MQConfig;
import com.nurs.rabbitmq.dto.AllOrdersRequest;
import com.nurs.rabbitmq.dto.DeleteOrderRequest;
import com.nurs.rabbitmq.dto.UpdateOrderRequest;
import com.nurs.rabbitmq.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final RabbitTemplate template;

    public void createOrder(BigDecimal amount) {
        Order order = new Order(LocalDateTime.now().toString(), amount, false);
        template.convertAndSend(MQConfig.EXCHANGE,
                               MQConfig.ROUTING_KEY, order);
    }

    public void deleteOrder(Long id) {
        DeleteOrderRequest deleteOrderRequest = new DeleteOrderRequest();
        deleteOrderRequest.setId(id);
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY, deleteOrderRequest);
    }

    public void updateOrder(UpdateOrderRequest updateOrderRequest) {
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY, updateOrderRequest);
    }

    public void getAllOrders() {
        AllOrdersRequest allOrdersRequest = new AllOrdersRequest();
        allOrdersRequest.setOrders(new ArrayList<>());
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY, allOrdersRequest);
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