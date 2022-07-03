package com.nurs.rabbitmq.service;


import com.nurs.rabbitmq.config.MQConfig;
import com.nurs.rabbitmq.dto.DeleteOrderRequest;
import com.nurs.rabbitmq.dto.PaymentRequestWithId;
import com.nurs.rabbitmq.dto.UpdateOrderRequest;
import com.nurs.rabbitmq.entity.Order;
import com.nurs.rabbitmq.exceptions.OrderNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    @Value("${baseUrl}")
    private String baseUrl;
    private final WebClient webClient;

    private final RabbitTemplate template;

    public void createOrder(BigDecimal amount, String email) {
        Order order = new Order(LocalDateTime.now().toString(), amount, false, email);
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY_1, order);
    }

    public void deleteOrder(Long id) {
        DeleteOrderRequest deleteOrderRequest = new DeleteOrderRequest();
        deleteOrderRequest.setId(id);
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY_2, deleteOrderRequest);
    }

    public void updateOrder(UpdateOrderRequest updateOrderRequest) {
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY_3, updateOrderRequest);
    }

    public List<Order> getAllOrders() {
        return List.of(Objects.requireNonNull(webClient.get()
                .uri(baseUrl + "/getOrder")
                .retrieve()
                .bodyToMono(Order[].class)
                .block()));
    }

    public Order getOrderById(Long id) {
        Order order = webClient.get()
                .uri(baseUrl + "/getOrderById/" + id)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
        if(order == null) throw new OrderNotFound();
        return order;
    }



    public void pay(Long orderId, String creditCardNumber) {
        getOrderById(orderId);
        PaymentRequestWithId response = new PaymentRequestWithId(orderId, creditCardNumber);
        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY_4, response);
    }
}