package com.example.book_service.dto;

public record BookDto(
        long id,
        String title,
        String author,
        int price,
        int stock
) {
}
