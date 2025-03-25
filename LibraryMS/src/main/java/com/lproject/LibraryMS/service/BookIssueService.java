package com.lproject.LibraryMS.service;

import com.lproject.LibraryMS.model.Book;
import com.lproject.LibraryMS.model.BookIssue;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookIssueService {
    BookIssue saveBookIssue(BookIssue bookIssue);
    BookIssue getBookIssueById(int user_id);
    List<BookIssue> getAllBookIssues();
    void deleteBookIssue(int id);
    void deleteByuserId(int user_id);
    List<Book> getBooksIssuedToUser(int user_id);
}
