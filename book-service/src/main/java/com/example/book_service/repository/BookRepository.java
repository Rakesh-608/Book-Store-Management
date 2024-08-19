package com.example.book_service.repository;

import com.example.book_service.domain.Book;
import com.example.book_service.exceptions.BookNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}


//private List<Book> books=new ArrayList<>();
//
//public Book save(Book book){
//    books.add(book);
//    return book;
//}
//
//public List<Book> findAll(){
//    return books;
//}
//
//public Book findById(long id){
//    Optional<Book> existingBook= books.stream().filter(book -> book.getId().equals(id)).findFirst();
//    if(existingBook.isPresent()){
//        return existingBook.get();
//    }else{
//        throw new BookNotFoundException("Book not found with id: "+id);
//    }
//}
//
//public void deleteById(long id){
//    books.removeIf(book -> book.getId().equals(id));
//}
//
//public Book findByTitle(String title){
//    Optional<Book> existingBook= books.stream().filter(book -> book.getTitle().equals(title)).findFirst();
//    if(existingBook.isPresent()){
//        return existingBook.get();
//    }else{
//        throw new BookNotFoundException("Book not found with title: "+title);
//    }
//}
//
//public Book updateBook(Book book){
//    Book existingBook=findById(book.getId());
//    existingBook.setTitle(book.getTitle());
//    existingBook.setAuthor(book.getAuthor());
//    existingBook.setPrice(book.getPrice());
//    existingBook.setStock(book.getStock());
//    return existingBook;
//}
//
//
