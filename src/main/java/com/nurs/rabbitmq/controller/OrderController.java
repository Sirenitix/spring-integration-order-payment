package com.nurs.rabbitmq.controller;

import com.nurs.rabbitmq.dto.OrderRequest;
import com.nurs.rabbitmq.dto.PaymentRequest;
import com.nurs.rabbitmq.dto.UpdateOrderRequest;
import com.nurs.rabbitmq.entity.Order;
import com.nurs.rabbitmq.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @GetMapping("/")
    public List<Order> getAllOrders() {
       return orderService.getAllOrders();
    }

    @GetMapping("/order/{id}")
    public Order getOrderById(@Max(10000000) @Min(0) @PathVariable Long id) {
        return orderService.getOrderById(id);
    }


    @PostMapping("/order")
    public ResponseEntity<String> createOrder(
            @Valid @RequestBody OrderRequest orderRequest) {
        orderService.createOrder(orderRequest.getAmount(), orderRequest.getEmail());
        return ResponseEntity.ok().body("Success");
    }
    @DeleteMapping("/order/{id}")
    public ResponseEntity<String> deleteOrder(@Max(10000000) @Min(0) @PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().body("Success");
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<String> updateOrder(@Valid @RequestBody UpdateOrderRequest updateOrderRequest,
                                              @Max(10000000) @Min(0) @PathVariable(name = "id") Long id ) {
        updateOrderRequest.setId(id);
        orderService.updateOrder(updateOrderRequest);
        return ResponseEntity.ok().body("Success");
    }


    @PostMapping("/order/{id}/payment")
    public ResponseEntity<String> pay(
            @PathVariable("id") Long orderId,
            @Valid @RequestBody PaymentRequest paymentRequest) {
        orderService.pay(orderId, paymentRequest.getCreditCardNumber());
        return ResponseEntity.ok().body("Success");
    }
}
