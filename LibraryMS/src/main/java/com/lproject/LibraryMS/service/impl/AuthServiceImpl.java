package com.lproject.LibraryMS.service.impl;

import com.lproject.LibraryMS.dto.LoginResponseDto;
import com.lproject.LibraryMS.model.User;
import com.lproject.LibraryMS.service.Authservice;
import com.lproject.LibraryMS.service.UserService;
import com.lproject.LibraryMS.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AuthServiceImpl implements Authservice {
    @Autowired
    private UserService userService;
    @Override
    public LoginResponseDto login(String username, String password, String role) {
        User user = userService.findByUsername(username);
        if (user == null || !user.getPassword().equals(password) || !user.getRole().equals(role)) {
            throw new RuntimeException("Login Failed");
        }
        String token = JwtUtil.generateToken(user);
        return new LoginResponseDto(token, role, (long) user.getId()); // Include user ID
    }
    //return user!=null&&user.getPassword().equals(password);

    @Override
    public boolean changePassword(String oldPassword, String newPassword) {

        return false;
    }
}

