package com.example.book_service.service;

import com.example.book_service.domain.Book;
import com.example.book_service.exceptions.BookNotFoundException;
import com.example.book_service.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book){
        bookRepository.save(book);
        return book;
    }

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public Book getBookById(long id){
        Optional<Book> existingBook= bookRepository.findById(id);
        if(existingBook.isPresent()){
            return existingBook.get();
        }else{
            throw new BookNotFoundException("Book not found with id: "+id);
        }
    }

    public void deleteBookById(long id){
        bookRepository.deleteById(id);
    }

    public Book updateBook(long id,Book book){
        Optional<Book> existingBook=bookRepository.findById(id);
        if(existingBook.isPresent()) {
            existingBook.get().setTitle(book.getTitle());
            existingBook.get().setAuthor(book.getAuthor());
            existingBook.get().setPrice(book.getPrice());
            existingBook.get().setStock(book.getStock());
            return bookRepository.save(existingBook.get());
        }
        throw new BookNotFoundException("No Book found with this id"+id);
    }


}
