package com.example.odev.service.Impl;

import com.example.odev.Repository.HaberRepository;
import com.example.odev.dto.HaberRequest;
import com.example.odev.dto.HaberResponse;
import com.example.odev.entity.Haber;
import com.example.odev.service.HaberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HaberServiceImpl implements HaberService {

    private final HaberRepository haberRepository;

    public HaberServiceImpl(HaberRepository haberRepository) {
        this.haberRepository = haberRepository;
    }

    @Override
    public HaberResponse haberGir(HaberRequest haberRequest) {

        Haber haber = new Haber();
        haber.setHaberLinki(haberRequest.getHaberLinki());
        haber.setIcerik(haberRequest.getIcerik());
        haber.setKonu(haberRequest.getKonu());
        haber.setGecerlilikTarihi(haberRequest.getGecerlilikTarihi());

        Haber toSave = haberRepository.save(haber);

        return mapToResponse(toSave);
    }

    @Override
    public HaberResponse haberGuncelle(Long haberId, HaberRequest haberRequest) {

        Haber haber = getHaberById(haberId);
        haber.setGecerlilikTarihi(haberRequest.getGecerlilikTarihi());
        haber.setKonu(haberRequest.getKonu());
        haber.setIcerik(haberRequest.getIcerik());
        haber.setHaberLinki(haberRequest.getHaberLinki());

        Haber toUpdate = haberRepository.save(haber);



        return mapToResponse(toUpdate);
    }

    @Override
    public List<HaberResponse> haberListesi() {

        List<Haber> habers = this.haberRepository.findAll();
        return mapToResponseList(habers);
    }

    @Override
    public Haber getHaberById(Long id) {
        return this.haberRepository.findById(id).orElseThrow(()-> new RuntimeException("haber bulunamadi"));
    }

    @Override
    public HaberResponse haberSil(Long id) {
        Haber haber = getHaberById(id);
        this.haberRepository.delete(haber);

        return mapToResponse(haber);

    }

    private HaberResponse mapToResponse(Haber haber){
        HaberResponse haberResponse = new HaberResponse();
        haberResponse.setHaberLinki(haber.getHaberLinki());
        haberResponse.setIcerik(haber.getIcerik());
        haberResponse.setKonu(haber.getKonu());
        haberResponse.setGecerlilikTarihi(haber.getGecerlilikTarihi());

        return haberResponse;
    }

    private List<HaberResponse> mapToResponseList(List<Haber> habers){
        List<HaberResponse> haberResponses = habers.
                stream().
                map(this::mapToResponse).
                collect(Collectors.toList());
        return haberResponses;
    }
}
