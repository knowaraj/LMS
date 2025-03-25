package com.lproject.LibraryMS.service;

import com.lproject.LibraryMS.dto.LoginResponseDto;


public interface Authservice {
    LoginResponseDto login(String username, String password, String role );
    boolean changePassword(String oldPassword,String newPassword);
}