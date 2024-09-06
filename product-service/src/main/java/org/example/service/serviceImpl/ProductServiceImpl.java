package org.example.service.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.model.dto.request.ProductRequest;
import org.example.model.dto.response.ProductResponse;
import org.example.model.entity.Product;
import org.example.repository.ProductRepository;
import org.example.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public List<ProductResponse> addProduct(List<ProductRequest> productRequest) {
        List<Product> products = productRequest.stream()
                .map(ProductRequest::toEntity)
                .collect(Collectors.toList());

        List<Product> saveProduct = productRepository.saveAll(products);

        return saveProduct.stream()
                .map(Product::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id).orElseThrow().toResponse();
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());

        Product saveProduct = productRepository.save(product);
        return saveProduct.toResponse();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getPrice()
                ))
                .collect(Collectors.toList());
    }
}
