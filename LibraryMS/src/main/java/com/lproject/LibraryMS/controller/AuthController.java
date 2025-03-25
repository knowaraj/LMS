package com.lproject.LibraryMS.controller;

import com.lproject.LibraryMS.dto.LoginDto;
import com.lproject.LibraryMS.dto.LoginResponseDto;
import com.lproject.LibraryMS.service.Authservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private Authservice authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        LoginResponseDto response = authService.login(
                loginDto.getUsername(), loginDto.getPassword(), loginDto.getRole()
        );
        return ResponseEntity.ok(response);
    }
}
