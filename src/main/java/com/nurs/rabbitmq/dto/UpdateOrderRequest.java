package com.nurs.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderRequest {


        @Max(10000000)
        @Min(0)
        private Long id;
        @NotNull
        @Size(min = 10, max = 30)
        private String date;
        @NotNull
        @DecimalMin(value = "0.0",inclusive = false)
        @DecimalMax(value = "10000000.0",inclusive = false)
        private BigDecimal amount;
        @NotNull
        private Boolean paid;
        @Email
        @NotNull
        private String email;

}
