package com.example.odev.service.Impl;

import com.example.odev.service.DosyaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class DosyaServiceImpl  implements DosyaService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public String dosyaEkle(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return null;
        }

        try {
            File klasor = new File(uploadDir);

            if (!klasor.exists()) {
                boolean created = klasor.mkdirs();
            }

            String dosyaAdi = UUID.randomUUID() + "_" + file.getOriginalFilename();

            File hedefDosya = new File(klasor, dosyaAdi);

            file.transferTo(hedefDosya);

            return hedefDosya.getAbsolutePath();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Dosya kaydedilemedi");
        }
    }


}
