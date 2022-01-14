package com.example.debt.service.entityservice;

import com.example.debt.dao.BorcDao;
import com.example.debt.entity.Borc;
import com.example.debt.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorcEntityService extends BaseEntityService<Borc, BorcDao> {
    public BorcEntityService(BorcDao dao){
        super(dao);
    }
/*    public Borc findAllByKalanBorcTutarı(){
       return getDao().findAllByKalanBorcTutarı();
    }*/
    public List<Borc> findByKullaniciIdOrderByAnaBorcTutari(Long kullaniciId){
        return getDao().findByKullaniciIdOrderByAnaBorcTutari(kullaniciId);
    }
/*    public List<Borc> findByKullaniciIdOrderByKalanBorcTutari(Long kullaniciId){
        return getDao().findByKullaniciIdOrderByKalanBorcTutari(kullaniciId);
    }*/
}
