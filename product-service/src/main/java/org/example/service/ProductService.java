package org.example.service;

import org.example.model.dto.request.ProductRequest;
import org.example.model.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> addProduct(List<ProductRequest> productRequest);

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    void deleteProduct(Long id);

    List<ProductResponse> getAllProduct();
}
