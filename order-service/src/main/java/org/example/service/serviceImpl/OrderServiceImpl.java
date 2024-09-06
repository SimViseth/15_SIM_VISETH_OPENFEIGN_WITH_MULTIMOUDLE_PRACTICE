package org.example.service.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.feign.CustomerFeign;
import org.example.feign.ProductFeign;
import org.example.model.dto.request.OrderRequest;
import org.example.model.dto.response.ApiResponse;
import org.example.model.dto.response.CustomerResponse;
import org.example.model.dto.response.OrderResponse;
import org.example.model.dto.response.ProductResponse;
import org.example.model.entity.Order;
import org.example.repository.OrderRepository;
import org.example.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerFeign customerFeign;
    private final ProductFeign productFeign;

    @Override
    public OrderResponse addOrder(OrderRequest orderRequest) {

        CustomerResponse customer = customerFeign.getCustomerById(orderRequest.getCustomerId()).getBody().getPayload();

        List<ProductResponse> products = orderRequest.getProductIds().stream()
                .map(productId -> productFeign.getProductById(productId).getBody().getPayload())
                .collect(Collectors.toList());

        Order order = new Order();
        order.setCustomerId(orderRequest.getCustomerId());
        order.setProductIds(orderRequest.getProductIds());
        order.setOrderDate(orderRequest.getOrderDate());
        orderRepository.save(order);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setCustomerId(customer);
        orderResponse.setProductIds(products);
        orderResponse.setOrderDate(order.getOrderDate());

        return orderResponse;

    }

    @Override
    public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {

        CustomerResponse customer = customerFeign.getCustomerById(orderRequest.getCustomerId()).getBody().getPayload();

        List<ProductResponse> products = orderRequest.getProductIds().stream()
                .map(productId -> productFeign.getProductById(productId).getBody().getPayload())
                .collect(Collectors.toList());

        Order order = orderRepository.findById(id)
                .orElseThrow(null);

        order.setCustomerId(orderRequest.getCustomerId());
        order.setProductIds(orderRequest.getProductIds());
        orderRepository.save(order);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setCustomerId(customer);
        orderResponse.setProductIds(products);
        orderResponse.setOrderDate(order.getOrderDate());

        return orderResponse;
    }

    @Override
    public List<OrderResponse> getAllOrder() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderList = new ArrayList<>();

        for (Order order : orders) {
            ResponseEntity<ApiResponse<CustomerResponse>> response = customerFeign.getCustomerById(order.getCustomerId());
            CustomerResponse customer = response.getBody().getPayload();

            List<ProductResponse> products = new ArrayList<>();
            for (Long productId : order.getProductIds()) {
                ResponseEntity<ApiResponse<ProductResponse>> productResponseEntity = productFeign.getProductById(productId);
                ProductResponse product = productResponseEntity.getBody().getPayload();
                products.add(product);
            }

            OrderResponse orderResponse = new OrderResponse(order.getId(), customer, products, order.getOrderDate());
            orderList.add(orderResponse);
        }
        return orderList;
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(null);

        if (order != null) {
            OrderResponse response = new OrderResponse();

            response.setId(order.getId());
            response.setOrderDate(order.getOrderDate());
            ResponseEntity<ApiResponse<CustomerResponse>> responseEntity = customerFeign.getCustomerById(order.getCustomerId());
            //CustomerResponse customer = responseEntity.getBody().getPayload();
            response.setCustomerId(responseEntity.getBody().getPayload());

            List<ProductResponse> products = new ArrayList<>();
            for (Long productId : order.getProductIds()) {
                ResponseEntity<ApiResponse<ProductResponse>> productResponseEntity = productFeign.getProductById(productId);
                ProductResponse product = productResponseEntity.getBody().getPayload();
                products.add(product);
            }

            response.setProductIds(products);

            return response;
        }
        return null;
    }

    @Override
    public void deleteOrder(Long id) {

        Order order = orderRepository.findById(id)
                        .orElseThrow(null);

        if (order != null) {
            orderRepository.deleteById(id);
        }
    }
}
