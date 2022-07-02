package com.nurs.rabbitmq.entity;

import lombok.*;
import javax.persistence.*;



@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private Order order;
    @NonNull
    private String creditCardNumber;
}
