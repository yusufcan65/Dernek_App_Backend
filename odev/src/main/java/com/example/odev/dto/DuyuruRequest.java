package com.example.odev.dto;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class DuyuruRequest {

    private String konu;
    private String icerik;
    private LocalDate gecerlilikTarihi;

    private MultipartFile resim;

    public String getKonu() {
        return konu;
    }

    public void setKonu(String konu) {
        this.konu = konu;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public LocalDate getGecerlilikTarihi() {
        return gecerlilikTarihi;
    }

    public void setGecerlilikTarihi(LocalDate gecerlilikTarihi) {
        this.gecerlilikTarihi = gecerlilikTarihi;
    }

    public MultipartFile getResim() {
        return resim;
    }

    public void setResim(MultipartFile resim) {
        this.resim = resim;
    }
}
