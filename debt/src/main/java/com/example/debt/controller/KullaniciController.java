package com.example.debt.controller;


import com.example.debt.entity.Kullanici;
import com.example.debt.service.KullaniciService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class KullaniciController {

    KullaniciService kullaniciService;

    @GetMapping
    public List<Kullanici> findAll(){
        return kullaniciService.findAllKullanici();
    }


    @PostMapping
    public Kullanici save(@RequestBody Kullanici kullanici){
        return kullaniciService.saveKullanici(kullanici);
    }
    @PutMapping
    public Kullanici update(@RequestBody Kullanici kullanici){
        return kullaniciService.updateKullanici(kullanici);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        kullaniciService.deleteKullanici(id);
    }

}
