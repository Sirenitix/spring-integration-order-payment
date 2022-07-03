package com.nurs.rabbitmq.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class OrderRequest {
    @DecimalMin(value = "0.0",inclusive = false)
    @DecimalMax(value = "10000000.0",inclusive = false)
    @NotNull
    private BigDecimal amount;
}


