package com.lproject.LibraryMS.dto;


public class LoginResponseDto {
    private String token;
    private String userRole;
    private Long id;

    public LoginResponseDto(String token, String userRole, Long id) {
        this.token = token;
        this.userRole = userRole;
        this.id = id; // Initialize ID
    }
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public LoginResponseDto(String token, String userRole) {
        this.token = token;
        this.userRole= userRole;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() { // Getter for ID
        return id;
    }

    public void setId(Long id) { // Setter for ID
        this.id = id;
    }
}