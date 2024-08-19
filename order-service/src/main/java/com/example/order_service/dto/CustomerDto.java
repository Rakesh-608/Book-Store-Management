package com.example.order_service.dto;

public record CustomerDto(
        long id,
        String name,
        String email,
        String phone
) {
}
