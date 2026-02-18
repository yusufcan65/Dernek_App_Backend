package com.example.odev.service.Impl;

import com.example.odev.Repository.UserRepository;
import com.example.odev.entity.User;
import com.example.odev.service.UserDetailService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Primary
public class UserDetailServiceImpl implements UserDetailService , UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        // Veritabanından kullanıcıyı ara
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + username));

        // Veritabanındaki 'Role' bilgisini Spring Security'nin anlayacağı 'GrantedAuthority' tipine çevir
        // Spring Security rollerin başında "ROLE_" ön eki olmasını bekler.
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());

        // Spring Security'nin kendi User nesnesini döndür
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(authority)
        );
    }
}
