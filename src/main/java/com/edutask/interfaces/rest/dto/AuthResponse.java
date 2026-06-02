package com.edutask.interfaces.rest.dto;

public class AuthResponse {
    public String token;
    public UserDto user;

    public static class UserDto {
        public String id;
        public String email;
        public String name;
    }
}