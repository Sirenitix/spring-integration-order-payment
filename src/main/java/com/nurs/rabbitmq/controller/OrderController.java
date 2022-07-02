package com.nurs.rabbitmq.controller;

import com.nurs.rabbitmq.dto.OrderRequest;
import com.nurs.rabbitmq.dto.PaymentRequest;
import com.nurs.rabbitmq.dto.PaymentResponse;
import com.nurs.rabbitmq.dto.UpdateOrderRequest;
import com.nurs.rabbitmq.entity.Order;
import com.nurs.rabbitmq.entity.Payment;
import com.nurs.rabbitmq.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

//    @GetMapping("/")
//    public void getAllOrders() {
//        orderService.getAllOrders();
//
//    }

    @PostMapping("/order")
    public void createOrder(
            @RequestBody @Valid OrderRequest orderRequest) {
        orderService.createOrder(orderRequest.getAmount());

    }
    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable(name = "id") @Valid Long id) {
        orderService.deleteOrder(id);

    }

    @PutMapping("/order/{id}")
    public void updateOrder(
            @RequestBody @Valid UpdateOrderRequest updateOrderRequest, @PathVariable(name = "id") @Valid Long id ) {
        updateOrderRequest.setId(id);
        orderService.updateOrder(updateOrderRequest);

    }

//    @GetMapping("/order/{id}")
//    public ResponseEntity<Order> getOrder(@PathVariable("id") Long orderId) {
//        Order order = orderService.getOrder(orderId);
//        return ResponseEntity.ok().body(order);
//    }
//
//    @PostMapping("/order/{id}/payment")
//    public ResponseEntity<PaymentResponse> pay(
//            @PathVariable("id") Long orderId,
//            @RequestBody @Valid PaymentRequest paymentRequest,
//            UriComponentsBuilder uriComponentsBuilder) {
//
//        Payment payment = orderService.pay(orderId, paymentRequest.getCreditCardNumber());
//        URI location = uriComponentsBuilder.path("/order/{id}/receipt").buildAndExpand(orderId).toUri();
//        PaymentResponse response = new PaymentResponse(payment.getOrder().getId(), payment.getCreditCardNumber());
//        return ResponseEntity.created(location).body(response);
//    }
}
