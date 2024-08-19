package com.example.book_service.controller;

import com.example.book_service.converter.BookDtoConverter;
import com.example.book_service.domain.Book;
import com.example.book_service.dto.BookDto;
import com.example.book_service.repository.BookRepository;
import com.example.book_service.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;
    private final BookDtoConverter converter;

    public BookController(BookService bookService, BookDtoConverter converter) {
        this.bookService = bookService;
        this.converter = converter;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        List<BookDto> dtos=new ArrayList<>();
        for (Book book:bookService.getAll()){
            dtos.add(converter.convertToDto(book));
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable long id){
        return converter.convertToDto(bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto dto){
        return ResponseEntity.ok(converter.convertToDto(bookService.addBook(converter.convertToEntity(dto))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto dto){
        return ResponseEntity.ok(converter.convertToDto(bookService.updateBook(id, converter.convertToEntity(dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}
