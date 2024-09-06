package org.example.service;

import org.example.model.dto.request.CustomerRequest;
import org.example.model.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);

    CustomerResponse getCustomerById(Long id);

    CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest);

    void deleteCustomer(Long id);

    List<CustomerResponse> getAllCustomer();
}
