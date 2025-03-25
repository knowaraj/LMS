package com.lproject.LibraryMS.controller;

import com.lproject.LibraryMS.model.Book;
import com.lproject.LibraryMS.model.BookIssue;
import com.lproject.LibraryMS.repository.BookIssueRepository;
import com.lproject.LibraryMS.repository.BookRepository;
import com.lproject.LibraryMS.service.BookIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.util.List;

@RestController
@RequestMapping("/bookissue")
@CrossOrigin(origins = "http://localhost:3000")
public class BookIssueController {

    @Autowired
    private BookIssueRepository repository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookIssueService bookIssueService;

    // Create a new book issue
    @PostMapping("/issue")
    public ResponseEntity<?> add(@RequestBody BookIssue bookIssue) {
        try {
            // Fetch the book from the database using the provided book ID
            Book book = bookRepository.findById(bookIssue.getBook().getBid())
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            // Check if the book is in stock (count > 0)
            if (book.getCount() <= 0) {
                return ResponseEntity.status(400).body("This book is out of stock.");
            }

            // Decrement the book stock count
           // book.setCount(book.getCount() - 1);

            // Save the updated book entity to the database
            bookRepository.save(book);

            // Save the book issue record
            BookIssue createdBookIssue = bookIssueService.saveBookIssue(bookIssue);

            return ResponseEntity.ok(createdBookIssue);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    // Get all book issues
    @GetMapping("/list")
    public List<BookIssue> getAll() {
        return repository.findAll();
    }

    // Delete a book issue by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        try {
            // Fetch the BookIssue object that needs to be deleted
            BookIssue bookIssue = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Book issue not found"));

            System.out.println("Found Book Issue: " + bookIssue);  // Add this line to log

            // Fetch the associated book
            Book book = bookIssue.getBook();

            // Increment the book stock count
            book.setCount(book.getCount() + 1);

            // Save the updated book entity
            bookRepository.save(book);

            // Delete the book issue record
            repository.deleteById(id);

            return ResponseEntity.ok("Book issue deleted successfully, book count updated.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting book issue: " + e.getMessage());
        }
    }


    @GetMapping("/user/{userId}/books")
    public ResponseEntity<?> getBooksIssuedToUser(@PathVariable int userId) {
        try {
            List<Book> books = bookIssueService.getBooksIssuedToUser(userId);
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching books for user: " + e.getMessage());
        }
    }
}
