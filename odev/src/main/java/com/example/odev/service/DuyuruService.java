package com.example.odev.service;

import com.example.odev.dto.DuyuruRequest;
import com.example.odev.dto.DuyuruResponse;

import java.util.List;

public interface DuyuruService {

    DuyuruResponse duyuruGir(DuyuruRequest duyuruRequest);
    DuyuruResponse duyuruGuncelle(Long duyuruId);

    List<DuyuruResponse> duyuruListesi();
    DuyuruResponse getDuyuruById(Long id);
}
