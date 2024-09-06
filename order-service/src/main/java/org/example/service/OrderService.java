package org.example.service;

import org.example.model.dto.request.OrderRequest;
import org.example.model.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse addOrder(OrderRequest orderRequest);

    OrderResponse updateOrder(Long id, OrderRequest orderRequest);

    List<OrderResponse> getAllOrder();

    OrderResponse getOrderById(Long id);

    void deleteOrder(Long id);
}
