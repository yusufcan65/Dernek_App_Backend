package com.example.odev.service;

import org.springframework.web.multipart.MultipartFile;

public interface DosyaService {

    String dosyaEkle(MultipartFile file);
}
