package com.example.customer_service.controller;

import com.example.customer_service.converter.CustomerDtoConverter;
import com.example.customer_service.domain.Customer;
import com.example.customer_service.dto.CustomerDto;
import com.example.customer_service.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerDtoConverter converter;

    public CustomerController(CustomerService customerService, CustomerDtoConverter converter) {
        this.customerService = customerService;
        this.converter = converter;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> dtos=new ArrayList<>();
        for(Customer  customer:customerService.getAllCustomers()){
            dtos.add(converter.convertToDto(customer));
        }
        return ResponseEntity.ok(dtos);
    }


    @GetMapping("/{id}")
    public CustomerDto getCustomerById(@PathVariable long id) {
        return converter.convertToDto(customerService.getCustomerById(id));
    }

    @PostMapping
    public CustomerDto addCustomer(CustomerDto dto) {
        return converter.convertToDto(customerService.addCustomer(converter.convertToEntity(dto)));

    }


    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(long id, Customer customer) {
        return ResponseEntity.ok(converter.convertToDto(customerService.updateCustomer(id, customer)));

    }

    @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCustomer(long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
