package com.example.odev.service;

import com.example.odev.dto.DuyuruRequest;
import com.example.odev.dto.DuyuruResponse;
import com.example.odev.entity.Duyuru;

import java.util.List;

public interface DuyuruService {

    DuyuruResponse duyuruGir(DuyuruRequest duyuruRequest);
    DuyuruResponse duyuruGuncelle(Long duyuruId, DuyuruRequest duyuruRequest);

    List<DuyuruResponse> duyuruListesi();
    Duyuru getDuyuruById(Long id);
    DuyuruResponse duyuruSil(Long id);
}
