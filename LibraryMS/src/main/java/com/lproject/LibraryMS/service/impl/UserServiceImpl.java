package com.lproject.LibraryMS.service.impl;

import com.lproject.LibraryMS.model.User;
import com.lproject.LibraryMS.repository.UserRepository;
import com.lproject.LibraryMS.service.BookIssueService;
import com.lproject.LibraryMS.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BookIssueService bookIssueService;

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        getById(id);
        userRepository.deleteById(id);
        bookIssueService.deleteByuserId(id);
    }

    @Override
    public User getById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new RuntimeException("User not Found"));
    }


    @Override
    public User update(User user, int id) {
        getById(id);
        user.setId(id);
        return userRepository.save(user);
    }
}

