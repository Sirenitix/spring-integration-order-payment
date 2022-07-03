package com.nurs.rabbitmq.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.*;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class OrderRequest {
    @DecimalMin(value = "0.0",inclusive = false)
    @DecimalMax(value = "10000000.0",inclusive = false)
    @NotNull
    private BigDecimal amount;

    @Email
    @NotNull
    private String email;
}


