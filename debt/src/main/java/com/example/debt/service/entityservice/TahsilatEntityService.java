package com.example.debt.service.entityservice;

import com.example.debt.dao.BorcDao;
import com.example.debt.dao.TahsilatDao;
import com.example.debt.dto.TahsilatDto;
import com.example.debt.entity.Borc;
import com.example.debt.entity.Tahsilat;
import com.example.debt.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TahsilatEntityService extends BaseEntityService<Tahsilat, TahsilatDao> {
    public TahsilatEntityService(TahsilatDao dao){
        super(dao);
    }


    public List<Tahsilat> findByOlusmaTarihiGreaterThanEqualAndOlusmaTarihiLessThanEqual(LocalDate startDate, LocalDate endDate){
        return getDao().findByOlusmaTarihiGreaterThanEqualAndOlusmaTarihiLessThanEqual(startDate,endDate);
    }

    public List<Tahsilat> findByKullaniciIdOrderByTahsilatTutari(Long kullaniciId){
        return getDao().findByKullaniciIdOrderByTahsilatTutari(kullaniciId);
    }
    public Tahsilat findByKullaniciAndTahsilatTutari(Long kullaniciId){
        return getDao().findTahsilatByKullanici(kullaniciId);
    }

}
