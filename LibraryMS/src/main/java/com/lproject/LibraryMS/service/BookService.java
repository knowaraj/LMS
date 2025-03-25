package com.lproject.LibraryMS.service;

import com.lproject.LibraryMS.model.Book;
import com.lproject.LibraryMS.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {
    Book add(Book book);
    List<Book> getAll();
    Book findByBookname(String bookname);
    void deleteByBid(int id);

    Book getById(int id);

    Book update(Book book, int id);
}
