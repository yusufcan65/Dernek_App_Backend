package com.example.odev.service.Impl;

import com.example.odev.Repository.UserRepository;
import com.example.odev.dto.AuthRequest;
import com.example.odev.dto.AuthResponse;
import com.example.odev.entity.User;
import com.example.odev.enums.Role;
import com.example.odev.service.AuthService;
import com.example.odev.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse register(AuthRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setRole(Role.ROLE_ADMIN);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User toSave = userRepository.save(user);
        String token = jwtService.generateToken(toSave.getUsername());

        return mapToResponse(toSave,token);

    }

    @Override
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())

        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()-> new RuntimeException("Kullanici Bulunamadi!"));

        String token = jwtService.generateToken(request.getUsername());
        return mapToResponse(user,token);
    }

    private AuthResponse mapToResponse(User user,String token){
        AuthResponse authResponse= new AuthResponse();
        authResponse.setUsername(user.getUsername());
        authResponse.setToken(token);
        return authResponse;
    }
}
