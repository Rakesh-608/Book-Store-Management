package com.example.order_service.service;

import com.example.order_service.domain.Order;
import com.example.order_service.exceptions.OrderNotFoundException;
import com.example.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    public Order getOrderById(long id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()) {
            return order.get();
        } else {
            throw new OrderNotFoundException("Order not found with id: " + id);
        }
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }


    public Order updateOrder(long id, Order order) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if(existingOrder.isPresent()) {
            order.setId(id);
            return orderRepository.save(order);
        } else {
            throw new OrderNotFoundException("Order not found with id: " + id);
        }
    }

    public void cancelOrder(long id) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if(existingOrder.isPresent()) {
            orderRepository.deleteById(id);
        } else {
            throw new OrderNotFoundException("Order not found with id: " + id);
        }
    }
}
