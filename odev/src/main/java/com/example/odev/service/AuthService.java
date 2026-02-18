package com.example.odev.service;

import com.example.odev.dto.AuthRequest;
import com.example.odev.dto.AuthResponse;

public interface AuthService {
    AuthResponse register(AuthRequest request);
    AuthResponse login(AuthRequest request);
}
