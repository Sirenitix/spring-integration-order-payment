package com.nurs.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class PaymentRequestWithId {
    private final Long orderId;
    private final String creditCardNumber;
}
