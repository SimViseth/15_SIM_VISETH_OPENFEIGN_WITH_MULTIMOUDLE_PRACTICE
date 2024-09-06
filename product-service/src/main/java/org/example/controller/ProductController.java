package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.dto.request.ProductRequest;
import org.example.model.dto.response.ApiResponse;
import org.example.model.dto.response.ProductResponse;
import org.example.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> addProduct(@RequestBody List<ProductRequest> productRequest) {
        List<ProductResponse> response = productService.addProduct(productRequest);
        ApiResponse<List<ProductResponse>> apiResponse = ApiResponse.<List<ProductResponse>>builder()
                .status(HttpStatus.CREATED)
                .message("A new Product is Created Successfully.")
                .payload(response)
                .localDate(LocalDate.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProduct() {
        List<ProductResponse> response = productService.getAllProduct();
        ApiResponse<List<ProductResponse>> apiResponse = ApiResponse.<List<ProductResponse>>builder()
                .status(HttpStatus.OK)
                .message("Get All Products Successfully.")
                .payload(response)
                .localDate(LocalDate.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Long id) {
        ProductResponse response = productService.getProductById(id);
        ApiResponse<ProductResponse> apiResponse = ApiResponse.<ProductResponse>builder()
                .status(HttpStatus.OK)
                .message("Get Product by id Successfully.")
                .payload(response)
                .localDate(LocalDate.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.updateProduct(id, productRequest);
        ApiResponse<ProductResponse> apiResponse = ApiResponse.<ProductResponse>builder()
                .status(HttpStatus.OK)
                .message("Product is Updated Successfully.")
                .payload(response)
                .localDate(LocalDate.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .status(HttpStatus.OK)
                .message("Product with id " + id + " is deleted successfully.")
                .localDate(LocalDate.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
