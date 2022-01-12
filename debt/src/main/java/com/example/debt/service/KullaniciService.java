package com.example.debt.service;

import com.example.debt.dao.KullaniciDao;
import com.example.debt.entity.Kullanici;
import com.example.debt.service.entityservice.KullaniciEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KullaniciService {

    private KullaniciEntityService kullaniciEntityService;

    public Kullanici saveKullanici(Kullanici kullanici){
        return kullaniciEntityService.save(kullanici);
    }

    public Kullanici updateKullanici(Kullanici kullanici) {

        kullanici = kullaniciEntityService.save(kullanici);
        return kullanici;
    }
    public Kullanici findKullaniciId(Long id){
       return kullaniciEntityService.findById(id);
    }

    public void deleteKullanici(Long id) {
        Kullanici kullanici = findKullaniciId(id);
        kullaniciEntityService.delete(kullanici);
    }
}
