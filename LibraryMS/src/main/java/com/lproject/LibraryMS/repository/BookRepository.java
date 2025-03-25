package com.lproject.LibraryMS.repository;

import com.lproject.LibraryMS.model.Book;
import com.lproject.LibraryMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository <Book,Integer>{

    Optional<Book> findByBid(int bid);

}
