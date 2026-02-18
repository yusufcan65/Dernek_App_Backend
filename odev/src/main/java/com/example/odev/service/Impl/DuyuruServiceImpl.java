package com.example.odev.service.Impl;

import com.example.odev.Repository.DuyuruRepository;
import com.example.odev.dto.DuyuruRequest;
import com.example.odev.dto.DuyuruResponse;
import com.example.odev.entity.Duyuru;
import com.example.odev.entity.Etkinlik;
import com.example.odev.enums.EtkinlikTipi;
import com.example.odev.pattern.factory.EtkinlikFactory;
import com.example.odev.service.DosyaService;
import com.example.odev.service.DuyuruService;
import com.example.odev.service.WebSocketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DuyuruServiceImpl implements DuyuruService {

    private final DuyuruRepository duyuruRepository;
    private final DosyaService dosyaService;

    private final WebSocketService webSocketService;
    private final EtkinlikFactory etkinlikFactory;

    public DuyuruServiceImpl(DuyuruRepository duyuruRepository, DosyaService dosyaService, WebSocketService webSocketService, EtkinlikFactory etkinlikFactory) {
        this.duyuruRepository = duyuruRepository;
        this.dosyaService = dosyaService;
        this.webSocketService = webSocketService;
        this.etkinlikFactory = etkinlikFactory;
    }

    @Override
    public DuyuruResponse duyuruGir(DuyuruRequest duyuruRequest) {

        String filePath = dosyaService.dosyaEkle(duyuruRequest.getResimYolu());

        Etkinlik etkinlik = etkinlikFactory.olustur(EtkinlikTipi.DUYURU);

        Duyuru duyuru = (Duyuru) etkinlik;

        duyuru.setIcerik(duyuruRequest.getIcerik());
        duyuru.setKonu(duyuruRequest.getKonu());
        duyuru.setGecerlilikTarihi(duyuruRequest.getGecerlilikTarihi());
        duyuru.setResimYolu(filePath);


        Duyuru toSave = duyuruRepository.save(duyuru);
        DuyuruResponse response = mapToResponse(toSave);
        webSocketService.duyuruGonder(response);

        return response;
    }

    @Override
    public DuyuruResponse duyuruGuncelle(Long duyuruId, DuyuruRequest duyuruRequest) {
        Duyuru duyuru = getDuyuruById(duyuruId);

        duyuru.setKonu(duyuruRequest.getKonu());
        duyuru.setIcerik(duyuruRequest.getIcerik());
        duyuru.setGecerlilikTarihi(duyuruRequest.getGecerlilikTarihi());
        String filePath = dosyaService.dosyaEkle(duyuruRequest.getResimYolu());
        duyuru.setResimYolu(filePath);

        Duyuru toUpdate = duyuruRepository.save(duyuru);

        return mapToResponse(toUpdate);
    }

    @Override
    public List<DuyuruResponse> duyuruListesi() {
        List<Duyuru> duyuruList = this.duyuruRepository.findAll();

        return mapToResponseList(duyuruList);
    }

    @Override
    public Duyuru getDuyuruById(Long id) {
        return this.duyuruRepository.findById(id).orElseThrow(()->new RuntimeException("duyuru bulunamadi"));
    }

    @Override
    public DuyuruResponse duyuruSil(Long id) {
        Duyuru duyuru = getDuyuruById(id);
        duyuruRepository.delete(duyuru);
        return mapToResponse(duyuru);
    }

    private DuyuruResponse mapToResponse(Duyuru duyuru){
        DuyuruResponse duyuruResponse = new DuyuruResponse();
        duyuruResponse.setId(duyuru.getId());
        duyuruResponse.setIcerik(duyuru.getIcerik());
        duyuruResponse.setKonu(duyuru.getKonu());
        duyuruResponse.setGecerlilikTarihi(duyuru.getGecerlilikTarihi());
        duyuruResponse.setResimYolu(duyuru.getResimYolu());
        duyuruResponse.setCreatedDate(duyuru.getCreatedDate());
        return duyuruResponse;
    }

    private List<DuyuruResponse> mapToResponseList(List<Duyuru> duyuruList){
        List<DuyuruResponse> duyuruResponses = duyuruList.
                stream().
                map(this::mapToResponse).
                collect(Collectors.toList());
        return duyuruResponses;
    }
}
