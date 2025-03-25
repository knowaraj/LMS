package com.lproject.LibraryMS.service;

import com.lproject.LibraryMS.model.User;

import java.util.List;

public interface UserService {

    User add(User user);
    List<User>  getAll();
    User findByUsername(String username);
    void deleteById(int id);
     User getById(int id);
    User update(User user,int id);

}
