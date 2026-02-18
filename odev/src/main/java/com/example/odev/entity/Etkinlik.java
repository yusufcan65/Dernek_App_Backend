package com.example.odev.entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table( name = "etkinlik")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Etkinlik extends BaseEntity {

    private String konu;
    @Column(columnDefinition = "TEXT")
    private String icerik;

    private LocalDate gecerlilikTarihi;


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




}
