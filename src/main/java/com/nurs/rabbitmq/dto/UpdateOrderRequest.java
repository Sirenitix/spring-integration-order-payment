package com.nurs.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderRequest {

        @NonNull
        @Max(10000000)
        @Min(0)
        private Long id;
        @NonNull
        @Size(min = 10, max = 30)
        private String date;
        @NonNull
        @DecimalMin(value = "0.0",inclusive = false)
        @DecimalMax(value = "10000000.0",inclusive = false)
        private BigDecimal amount;
        @NonNull
        private Boolean paid;
        @Email
        @NonNull
        private String email;

}
