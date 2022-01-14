package com.example.debt.dao;

import com.example.debt.entity.Borc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorcDao extends JpaRepository<Borc,Long>{

    //Borc findAllByKalanBorcTutarı();
    List<Borc> findByKullaniciIdOrderByAnaBorcTutari(Long kullaniciId);

    List<Borc> findAllByKullaniciIdOrderByKalanBorcTutari(Long kullaniciId);
    List<Borc> findByOlusmaTarihiGreaterThanEqualAndOlusmaTarihiLessThanEqual(LocalDate startDate, LocalDate endDate);
    Borc findByKullaniciIdOrderByKalanBorcTutari(Long kullaniciId);




}
