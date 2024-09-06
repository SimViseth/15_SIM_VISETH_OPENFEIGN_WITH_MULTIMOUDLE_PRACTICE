package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.OrderRequest;
import org.example.model.dto.response.ApiResponse;
import org.example.model.dto.response.CustomerResponse;
import org.example.model.dto.response.OrderResponse;
import org.example.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> addOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse response = orderService.addOrder(orderRequest);
        ApiResponse<OrderResponse> apiResponse = ApiResponse.<OrderResponse>builder()
                .status(HttpStatus.CREATED)
                .message("A new Order is created successfully.")
                .payload(response)
                .localDate(LocalDate.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> updateOrder(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
        OrderResponse response = orderService.updateOrder(id, orderRequest);
        ApiResponse<OrderResponse> apiResponse = ApiResponse.<OrderResponse>builder()
                .status(HttpStatus.OK)
                .message("Order updated successfully.")
                .payload(response)
                .localDate(LocalDate.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getAllOrder() {
        List<OrderResponse> response = orderService.getAllOrder();
        ApiResponse<List<OrderResponse>> apiResponse = ApiResponse.<List<OrderResponse>>builder()
                .status(HttpStatus.OK)
                .message("Get All orders successfully.")
                .payload(response)
                .localDate(LocalDate.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(@PathVariable Long id) {
        OrderResponse response = orderService.getOrderById(id);
        ApiResponse<OrderResponse> apiResponse = ApiResponse.<OrderResponse>builder()
                .status(HttpStatus.OK)
                .message("Get order by id successfully.")
                .payload(response)
                .localDate(LocalDate.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .status(HttpStatus.OK)
                .message("Order with id " + id + " is deleted successfully.")
                .localDate(LocalDate.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
