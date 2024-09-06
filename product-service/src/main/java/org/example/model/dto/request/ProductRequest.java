package org.example.model.dto.request;

import lombok.*;
import org.example.model.entity.Product;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private String name;
    private double price;

    public Product toEntity() {
        return new Product(null, this.name, this.price);
    }
}
