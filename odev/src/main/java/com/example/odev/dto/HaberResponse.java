package com.example.odev.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HaberResponse {

    private Long id;
    private String konu;
    private String icerik;
    private LocalDate gecerlilikTarihi;
    private String haberLinki;

    private LocalDateTime createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getHaberLinki() {
        return haberLinki;
    }

    public void setHaberLinki(String haberLinki) {
        this.haberLinki = haberLinki;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
