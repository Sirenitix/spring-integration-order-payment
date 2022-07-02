package com.nurs.rabbitmq.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

    @Data
    @NoArgsConstructor
    public class OrderRequest {
        @NotNull
        private BigDecimal amount;
    }


