package org.example.feign;

import org.example.model.dto.response.ApiResponse;
import org.example.model.dto.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "http://localhost:8081/api/v1/customer")
public interface CustomerFeign {

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(@PathVariable Long id);
}
