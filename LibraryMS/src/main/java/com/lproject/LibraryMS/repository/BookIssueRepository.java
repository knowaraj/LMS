package com.lproject.LibraryMS.repository;

import com.lproject.LibraryMS.model.BookIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookIssueRepository extends JpaRepository<BookIssue, Integer> {
    void deleteByuser_Id(int user_ID);
    List<BookIssue> findByUserId(int user_Id);
}
