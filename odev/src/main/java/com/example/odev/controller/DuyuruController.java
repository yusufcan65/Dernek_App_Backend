package com.example.odev.controller;


import com.example.odev.dto.DuyuruRequest;
import com.example.odev.dto.DuyuruResponse;
import com.example.odev.service.DuyuruService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/duyuru")
public class DuyuruController {

    private final DuyuruService duyuruService;


    public DuyuruController(DuyuruService duyuruService) {
        this.duyuruService = duyuruService;
    }

    @PostMapping( value = "/ekle", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DuyuruResponse> duyuruEkle( @RequestParam String konu,
                                                      @RequestParam String icerik,
                                                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate gecerlilikTarihi,
                                                      @RequestParam MultipartFile resimYolu
    ) {
        DuyuruRequest duyuruRequest = new DuyuruRequest();
        duyuruRequest.setKonu(konu);
        duyuruRequest.setIcerik(icerik);
        duyuruRequest.setGecerlilikTarihi(gecerlilikTarihi);
        duyuruRequest.setResimYolu(resimYolu);

        DuyuruResponse duyuruResponse = duyuruService.duyuruGir(duyuruRequest);

        return new ResponseEntity<>(duyuruResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DuyuruResponse>> duyurular(){
        List<DuyuruResponse> duyurular = duyuruService.duyuruListesi();
        return new ResponseEntity<>(duyurular,HttpStatus.OK);
    }

    @PutMapping(value = "/guncelle/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DuyuruResponse> duyuruGuncelle(
            @PathVariable Long id,
            @RequestParam String konu,
            @RequestParam String icerik,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate gecerlilikTarihi,
            @RequestParam(required = false) MultipartFile resimYolu
    ) {
        DuyuruRequest duyuruRequest = new DuyuruRequest();
        duyuruRequest.setKonu(konu);
        duyuruRequest.setIcerik(icerik);
        duyuruRequest.setGecerlilikTarihi(gecerlilikTarihi);
        duyuruRequest.setResimYolu(resimYolu);

        DuyuruResponse duyuruResponse = duyuruService.duyuruGuncelle(id, duyuruRequest);
        return new ResponseEntity<>(duyuruResponse, HttpStatus.OK);
    }
    @DeleteMapping("/sil/{id}")
    public ResponseEntity<DuyuruResponse> duyuruSil(@PathVariable Long id){
        DuyuruResponse duyuruResponse = duyuruService.duyuruSil(id);
        return new ResponseEntity<>(duyuruResponse,HttpStatus.OK);
    }

}
