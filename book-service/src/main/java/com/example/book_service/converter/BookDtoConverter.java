package com.example.book_service.converter;


import com.example.book_service.domain.Book;
import com.example.book_service.dto.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookDtoConverter {

    public BookDto convertToDto(Book book){
        BookDto bookDto=new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getStock()
        );
        return bookDto;
    }

    public Book convertToEntity(BookDto dto){
        Book book=new Book(
                dto.id(),
                dto.title(),
                dto.author(),
                dto.price(),
                dto.stock()
        );
        return book;
    }

}
