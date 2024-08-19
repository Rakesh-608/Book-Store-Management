package com.example.customer_service.service;

import com.example.customer_service.domain.Customer;
import com.example.customer_service.exceptions.CustomerNotFoundException;
import com.example.customer_service.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    public Customer getCustomerById(Long id) {
        Optional<Customer> optionalCustomer =customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            return optionalCustomer.get();
        }else{
            throw new CustomerNotFoundException("Customer not found with id: "+id);
        }
    }
    
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
//        return customer;
    }
    
    public Customer updateCustomer(Long id, Customer customerDetails) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            customer.setName(customerDetails.getName());
            customer.setEmail(customerDetails.getEmail());
            return customerRepository.save(customer);
        }else{
            throw new CustomerNotFoundException("Customer not found with id: "+id);
        }
    }


    public void deleteCustomer(long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            customerRepository.delete(optionalCustomer.get());
        }else{
            throw new CustomerNotFoundException("Customer not found with id: "+id);
        }
    }
}
