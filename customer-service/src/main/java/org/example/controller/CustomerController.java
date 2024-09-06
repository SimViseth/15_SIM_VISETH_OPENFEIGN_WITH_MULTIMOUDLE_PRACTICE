package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.CustomerRequest;
import org.example.model.dto.response.ApiResponse;
import org.example.model.dto.response.CustomerResponse;
import org.example.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse response = customerService.createCustomer(customerRequest);
        ApiResponse<CustomerResponse> apiResponse = ApiResponse.<CustomerResponse>builder()
                .status(HttpStatus.CREATED)
                .message("A new Customer is created successfully.")
                .payload(response)
                .localDate(LocalDate.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomer() {
        List<CustomerResponse> response = customerService.getAllCustomer();
        ApiResponse<List<CustomerResponse>> apiResponse = ApiResponse.<List<CustomerResponse>>builder()
                .status(HttpStatus.OK)
                .message("Get All Customers successfully.")
                .payload(response)
                .localDate(LocalDate.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(@PathVariable Long id) {
        CustomerResponse response = customerService.getCustomerById(id);
        ApiResponse<CustomerResponse> apiResponse = ApiResponse.<CustomerResponse>builder()
                .status(HttpStatus.OK)
                .message("Get Customer by id successfully.")
                .payload(response)
                .localDate(LocalDate.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
        CustomerResponse response = customerService.updateCustomer(id, customerRequest);
        ApiResponse<CustomerResponse> apiResponse = ApiResponse.<CustomerResponse>builder()
                .status(HttpStatus.OK)
                .message("Customer updated successfully.")
                .payload(response)
                .localDate(LocalDate.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .status(HttpStatus.OK)
                .message("Customer with id " + id + " is deleted successfully.")
                .localDate(LocalDate.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
