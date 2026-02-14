package com.example.odev.controller;

import com.example.odev.dto.HaberRequest;
import com.example.odev.dto.HaberResponse;
import com.example.odev.entity.Haber;
import com.example.odev.service.HaberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping()
    public ResponseEntity<List<HaberResponse>> haberler(){
        List<HaberResponse> haberResponseList = haberService.haberListesi();
        return new ResponseEntity<>(haberResponseList,HttpStatus.OK);
    }

    @DeleteMapping("/sil/{id}")
    public ResponseEntity<HaberResponse> haberSil(@PathVariable Long id){
        HaberResponse haberResponse = haberService.haberSil(id);
        return new ResponseEntity<>(haberResponse,HttpStatus.OK);
    }
}
