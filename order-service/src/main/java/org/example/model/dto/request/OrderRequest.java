package org.example.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.entity.Order;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long customerId;
    private List<Long> productIds;
    private LocalDate orderDate;

    public Order toEntity() {
        return new Order(null, this.customerId, this.productIds, this.orderDate);
    }
}