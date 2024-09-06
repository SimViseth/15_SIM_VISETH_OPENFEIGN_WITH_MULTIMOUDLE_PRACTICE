package org.example.feign;

import org.example.model.dto.response.ApiResponse;
import org.example.model.dto.response.CustomerResponse;
import org.example.model.dto.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service", url = "http://localhost:8082/api/v1/product")
public interface ProductFeign {
    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Long id);
}
