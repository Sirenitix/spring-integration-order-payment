package com.nurs.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteOrderRequest {

    @NotNull
    @Max(10000000)
    @Min(0)
    private Long id;
}
