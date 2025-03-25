package com.lproject.LibraryMS.controller;

import com.lproject.LibraryMS.model.Book;
import com.lproject.LibraryMS.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired //dependency injection
    private BookService bookService;

    @GetMapping("/list")
    public List<Book> getAllBook() {
        return bookService.getAll();
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.add(book);
    }
    @DeleteMapping("/delete/{id}")
    public Map<String,Boolean> deleteById(@PathVariable int id) {
        bookService.deleteByBid(id);
        return Map.of("Success",true);
    }
    @PutMapping("/update/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        return bookService.update(updatedBook, id);
    }
}