package org.example.model.dto.request;

import jakarta.persistence.Entity;
import lombok.*;
import org.example.model.entity.Customer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {
    private String name;
    private String email;

    public Customer toEntity() {
        return new Customer(null, this.name, this.email);
    }
}
