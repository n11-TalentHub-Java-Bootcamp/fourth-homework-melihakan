package com.example.debt.controller;

import com.example.debt.dto.BorcDto;
import com.example.debt.entity.Borc;
import com.example.debt.service.BorcService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/borclar")
@AllArgsConstructor
public class BorcController {

    private BorcService borcService;

    @PostMapping
    public ResponseEntity save(@RequestBody BorcDto borcDto){
        return borcService.save(borcDto);

    }
    @GetMapping
    public List<BorcDto> findByAll(){
        return borcService.findByAll();
    }
    @GetMapping("/user/{kullaniciId}")
    public List<BorcDto> findAllBorcByKullaniciId(@PathVariable Long kullaniciId){
        return borcService.findAllBorcByKullaniciId(kullaniciId);
    }
    @GetMapping("/user/anaborc/{kullaniciId}")
    public MappingJacksonValue findAllAnaBorcByKullanaciId(@PathVariable Long kullaniciId){
        return borcService.findAnaBorcByKullaniciId(kullaniciId);
    }
/*    @GetMapping("/user/vadeborc/{kullaniciId}")
    public List<BorcDto> findAllVadeBorcByKullanaciId(@PathVariable Long kullaniciId){
        return borcService.findAllVadeBorcByKullanaciId(kullaniciId);
    }*/

}
