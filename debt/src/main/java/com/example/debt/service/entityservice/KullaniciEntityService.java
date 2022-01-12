package com.example.debt.service.entityservice;

import com.example.debt.dao.KullaniciDao;
import com.example.debt.entity.Kullanici;
import com.example.debt.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class KullaniciEntityService extends BaseEntityService<Kullanici, KullaniciDao> {
    public KullaniciEntityService(KullaniciDao dao){
        super(dao);
    }
}
