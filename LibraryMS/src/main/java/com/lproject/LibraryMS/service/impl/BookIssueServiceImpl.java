package com.lproject.LibraryMS.service.impl;

import com.lproject.LibraryMS.model.Book;
import com.lproject.LibraryMS.model.BookIssue;
import com.lproject.LibraryMS.model.User;
import com.lproject.LibraryMS.repository.BookIssueRepository;
import com.lproject.LibraryMS.repository.BookRepository;
import com.lproject.LibraryMS.repository.UserRepository;
import com.lproject.LibraryMS.service.BookIssueService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookIssueServiceImpl implements BookIssueService {

    private final BookIssueRepository bookIssueRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookIssueServiceImpl(BookIssueRepository bookIssueRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.bookIssueRepository = bookIssueRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public BookIssue saveBookIssue(BookIssue bookIssue){
        int userId = bookIssue.getUser().getId(); // Assuming 'uid' is the User ID field
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        // Fetch the book by ID to check its stock
        Optional<Book> optionalBook = bookRepository.findByBid(bookIssue.getBook().getBid());

        Book book = optionalBook.orElseThrow(()-> new RuntimeException("Book not Found"));

        // Check if the book is in stock
        if (book.getCount() <= 0) {
            System.out.println("error");
        }

        // Decrement the book count by 1
        book.setCount(book.getCount() - 1);
        bookRepository.save(book); // Save the updated book

        // Now save the book issue record
        return bookIssueRepository.save(bookIssue);
    }

    @Override
    public BookIssue getBookIssueById(int id) {
        return bookIssueRepository.findById(id).orElse(null);
    }

    @Override
    public List<BookIssue> getAllBookIssues() {
        return bookIssueRepository.findAll();
    }

    @Override
    public void deleteBookIssue(int id) {
        bookIssueRepository.deleteById(id);
    }

    @Override
    public void deleteByuserId(int user_id) {
        bookIssueRepository.deleteByuser_Id(user_id);
    }

    @Override
    public List<Book> getBooksIssuedToUser(int user_id) {
        List<BookIssue> bookIssues = bookIssueRepository.findByUserId(user_id);
        return bookIssues.stream()
                .map(BookIssue::getBook) // Extract Book from each BookIssue
                .toList();
    }
}
