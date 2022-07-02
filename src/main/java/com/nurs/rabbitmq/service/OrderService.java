package com.nurs.rabbitmq.service;


import com.nurs.rabbitmq.config.MQConfig;
import com.nurs.rabbitmq.dto.DeleteOrderRequest;
import com.nurs.rabbitmq.dto.UpdateOrderRequest;
import com.nurs.rabbitmq.entity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {


    @Value("${baseUrl}")
    private String baseUrl;
    private final WebClient webClient;

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

    public List<Order> getAllOrders() {
        return List.of(Objects.requireNonNull(webClient.get()
                .uri(baseUrl + "/getOrder")
                .retrieve()
                .bodyToMono(Order[].class)
                .block()));
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