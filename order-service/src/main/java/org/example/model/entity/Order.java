package org.example.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.dto.response.CustomerResponse;
import org.example.model.dto.response.OrderResponse;
import org.example.model.dto.response.ProductResponse;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    @ElementCollection
    private List<Long> productIds;
    private LocalDate orderDate;

    public OrderResponse toResponse(CustomerResponse customerResponse, List<ProductResponse> productResponses) {
        return new OrderResponse(this.id, customerResponse, productResponses , this.orderDate);
    }

}
