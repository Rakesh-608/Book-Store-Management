package com.example.customer_service.converter;

import com.example.customer_service.domain.Customer;
import com.example.customer_service.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {
    public CustomerDto convertToDto(Customer customer) {
        return new CustomerDto(customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail());
    }


    public Customer convertToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.id());
        customer.setName(customerDto.name());
        customer.setPhone(customerDto.phone());
        customer.setEmail(customerDto.email());
        return customer;
    }
}
