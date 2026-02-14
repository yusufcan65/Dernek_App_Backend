package com.example.odev.service;

import com.example.odev.dto.HaberRequest;
import com.example.odev.dto.HaberResponse;
import com.example.odev.entity.Haber;

import java.util.List;

public interface HaberService {

    HaberResponse haberGir(HaberRequest haberRequest);
    HaberResponse haberGuncelle(Long haberId, HaberRequest haberRequest);
    List<HaberResponse> haberListesi();
    Haber getHaberById( Long id);

    HaberResponse haberSil(Long id);
}
