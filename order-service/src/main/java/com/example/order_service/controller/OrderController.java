package com.example.order_service.controller;

import com.example.order_service.clients.BookServiceClient;
import com.example.order_service.clients.CustomerServiceClient;
import com.example.order_service.converter.OrderDtoConverter;
import com.example.order_service.domain.Order;
import com.example.order_service.dto.BookDto;
import com.example.order_service.dto.CustomerDto;
import com.example.order_service.dto.OrderDto;
import com.example.order_service.exceptions.OutOfStockException;
import com.example.order_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderDtoConverter converter;
    private final BookServiceClient bookServiceClient;
    private final CustomerServiceClient customerServiceClient;

    public OrderController(OrderService orderService, OrderDtoConverter converter, BookServiceClient bookServiceClient, CustomerServiceClient customerServiceClient) {
        this.orderService = orderService;
        this.converter = converter;
        this.bookServiceClient = bookServiceClient;
        this.customerServiceClient = customerServiceClient;
    }

//    @GetMapping
//    public ResponseEntity<List<OrderDto>> getAllOrders() {
//        List<OrderDto> dtos=new ArrayList<>();
//        for(Order order:orderService.getAllOrders()){
//            dtos.add(converter.convertToOrderDto(order));
//        }
//        return ResponseEntity.ok(dtos);
//    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable long id) {
        Order order= orderService.getOrderById(id);
        OrderDto dto=new OrderDto(
                order.getId(),
                customerServiceClient.getCustomerById(order.getCustomerId()),
                bookServiceClient.getBookById(order.getBookId()),
                order.getQuantity(),
                order.getStatus()
        );

        return dto;
    }

    @PostMapping
    public OrderDto createOrder(Order order) {
        Order order1=new Order(
                order.getId(),
                order.getCustomerId(),
                order.getBookId(),
                order.getQuantity(),
                order.getStatus()
        );
        order1=orderService.createOrder(order1);

        BookDto bookDto=bookServiceClient.getBookById(order1.getBookId());

        if(bookDto.stock()<order1.getQuantity()){
            throw new OutOfStockException("Stock is not enough");
        }

        OrderDto dto=new OrderDto(
                order1.getId(),
                customerServiceClient.getCustomerById(order1.getCustomerId()),
                bookServiceClient.getBookById(order1.getBookId()),
                order1.getQuantity(),
                order1.getStatus()
        );
        return dto;
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<OrderDto> updateOrder(long id, Order order) {
//        return ResponseEntity.ok(converter.convertToOrderDto(orderService.updateOrder(id, order)));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> cancelOrder(long id) {
//        orderService.cancelOrder(id);
//        return ResponseEntity.noContent().build();
//    }
}
