package com.nurs.rabbitmq.dto;

import com.nurs.rabbitmq.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllOrdersRequest {
        @NonNull
        private List<Order> orders;
}
