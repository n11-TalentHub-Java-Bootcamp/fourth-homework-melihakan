package com.example.debt.dao;

import com.example.debt.dto.TahsilatDto;
import com.example.debt.entity.Borc;
import com.example.debt.entity.Tahsilat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TahsilatDao extends JpaRepository<Tahsilat,Long> {


    List<Tahsilat> findByOlusmaTarihiGreaterThanEqualAndOlusmaTarihiLessThanEqual(LocalDate startDate, LocalDate endDate);
    List<Tahsilat> findByKullaniciIdOrderByTahsilatTutari(Long kullaniciId);
    Tahsilat findTahsilatByKullanici(Long kullaniciId);

    /*List<Tahsilat> findByKullaniciIdOrderByKalanBorcTutari(Long kullaniciId);*/
}
