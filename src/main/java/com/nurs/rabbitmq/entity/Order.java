package com.nurs.rabbitmq.entity;

import lombok.*;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @NonNull
    private String date;
    @NonNull
    private BigDecimal amount;
    @NonNull
    private Boolean paid;
    @NonNull
    private String email;

}
