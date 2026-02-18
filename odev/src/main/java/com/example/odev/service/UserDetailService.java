package com.example.odev.service;

import com.example.odev.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserDetailService {
    UserDetails loadUserByUsername(String username);

}
