package com.example.debt.service;

import com.example.debt.dao.KullaniciDao;
import com.example.debt.entity.Kullanici;
import com.example.debt.service.entityservice.KullaniciEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KullaniciService {

    private KullaniciEntityService kullaniciEntityService;

    public List<Kullanici> findAllKullanici(){
        return kullaniciEntityService.findAll();
    }

    public Kullanici saveKullanici(Kullanici kullanici){
        return kullaniciEntityService.save(kullanici);
    }

    public Kullanici updateKullanici(Kullanici kullanici) {

        kullanici = kullaniciEntityService.save(kullanici);
        return kullanici;
    }
    public Kullanici findKullaniciById(Long id){
       return kullaniciEntityService.findKullaniciById(id);
    }

    public void deleteKullanici(Long id) {
        Kullanici kullanici = findKullaniciById(id);
        kullaniciEntityService.delete(kullanici);
    }
}
