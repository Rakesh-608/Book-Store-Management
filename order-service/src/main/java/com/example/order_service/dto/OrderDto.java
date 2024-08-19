package com.example.order_service.dto;

public record OrderDto(
        long id,
        CustomerDto customerDto,
        BookDto bookDto,
        int quantity,
        String status
) {
}
