package com.example.odev.controller;

import com.example.odev.dto.HaberRequest;
import com.example.odev.dto.HaberResponse;
import com.example.odev.service.HaberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/haber")
public class HaberController {

    private final HaberService haberService;

    public HaberController(HaberService haberService) {
        this.haberService = haberService;
    }

    @PostMapping("/ekle")
    public ResponseEntity<HaberResponse> haberEkle( @RequestBody HaberRequest haberRequest){
        HaberResponse haberResponse = haberService.haberGir(haberRequest);
        return new ResponseEntity<>(haberResponse,HttpStatus.OK);
    }
}
