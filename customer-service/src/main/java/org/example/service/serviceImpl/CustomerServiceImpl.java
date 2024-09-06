package org.example.service.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.model.dto.request.CustomerRequest;
import org.example.model.dto.response.CustomerResponse;
import org.example.model.entity.Customer;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        return customerRepository.save(customerRequest.toEntity()).toResponse();
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow().toResponse();
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());

        Customer saveCustomer = customerRepository.save(customer);
        return saveCustomer.toResponse();
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(null);

        if (customer != null) {
            customerRepository.deleteById(id);
        }
    }

    @Override
    public List<CustomerResponse> getAllCustomer() {
        List<Customer> customerList = customerRepository.findAll();

        return customerList.stream()
                .map(customer -> new CustomerResponse(
                        customer.getId(),
                        customer.getName(),
                        customer.getEmail()
                ))
                .collect(Collectors.toList());
    }
}
