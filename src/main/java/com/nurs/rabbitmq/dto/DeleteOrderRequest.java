package com.nurs.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteOrderRequest {

    @NonNull
    @Max(10000000)
    @Min(0)
    private Long id;
}
