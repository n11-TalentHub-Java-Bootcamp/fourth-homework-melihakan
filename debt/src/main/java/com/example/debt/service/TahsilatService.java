package com.example.debt.service;

import com.example.debt.converter.BorcConverter;
import com.example.debt.converter.TahsilatConverter;
import com.example.debt.dto.BorcDto;
import com.example.debt.dto.TahsilatDto;
import com.example.debt.entity.Borc;
import com.example.debt.entity.Kullanici;
import com.example.debt.entity.Tahsilat;
import com.example.debt.service.entityservice.TahsilatEntityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TahsilatService {

    private TahsilatEntityService tahsilatEntityService;
    private BorcService borcService;

    public TahsilatDto save(TahsilatDto tahsilatDto){

        List<BorcDto> allByid = borcService.findAllByid(tahsilatDto.getBorcId());
        for (BorcDto borcDto : allByid) {

            double v = borcDto.getKalanBorcTutari() - borcDto.getAnaBorcTutari();
            if(v == 0){
                Tahsilat tahsilat = TahsilatConverter.INSTANCE.convertTahsilatDtoToTahsilat(tahsilatDto);
                tahsilatEntityService.save(tahsilat);
                borcService.updateBorc(borcDto);
                LocalDate now = LocalDate.now();
                borcDto.setOlusmaTarihi(now);

            }else {

                borcService.updateBorc(borcDto);
                Tahsilat tahsilat = TahsilatConverter.INSTANCE.convertTahsilatDtoToTahsilat(tahsilatDto);
                tahsilat.setTahsilatTutari(tahsilat.getTahsilatTutari()-v);
                tahsilatEntityService.save(tahsilat);
                BorcDto dto = new BorcDto();
                dto.setKalanBorcTutari(0.0);
                dto.setAnaBorcTutari(v);
                dto.setVadeTarihi(null);
                dto.setOlusmaTarihi(LocalDate.now());
                dto.setKullaniciId(borcDto.getKullaniciId());
                Borc borc1 = borcService.saveGecikmeBorc(dto);
                Tahsilat tahsilat1 = new Tahsilat();
                tahsilat1.setTahsilatTutari(v);
                tahsilat1.setOlusmaTarihi(LocalDate.now());
                tahsilat1.setBorc(borc1);
                Kullanici kullanici = new Kullanici();
                kullanici.setId(borcDto.getKullaniciId());
                tahsilat1.setKullanici(kullanici);
                tahsilatEntityService.save(tahsilat1);

            }
        }
        return tahsilatDto;
    }

    public List<TahsilatDto> findByOlusmaTarihiGreaterThanEqualAndOlusmaTarihiLessThanEqual(LocalDate startDate, LocalDate endDate){
        List<Tahsilat> tahsilats = tahsilatEntityService.findByOlusmaTarihiGreaterThanEqualAndOlusmaTarihiLessThanEqual(startDate,endDate);
        List<TahsilatDto> tahsilatDtos = TahsilatConverter.INSTANCE.convertAllTahsilatListToTahsilatDtoList(tahsilats);

        return tahsilatDtos;
    }
    public List<TahsilatDto> findByKullaniciIdOrderByTahsilatTutari(Long kullaniciId){
        List<Tahsilat> tahsilatList = tahsilatEntityService.findByKullaniciIdOrderByTahsilatTutari(kullaniciId);
        List<TahsilatDto> tahsilatDtoList = TahsilatConverter.INSTANCE.convertAllTahsilatListToTahsilatDtoList(tahsilatList);
        return tahsilatDtoList;
    }
    public TahsilatDto findByKullaniciAndTahsilatTutari(Long kullaniciId){
        Tahsilat byKullaniciAndTahsilatTutari = tahsilatEntityService.findByKullaniciAndTahsilatTutari(kullaniciId);
        TahsilatDto tahsilatDto = TahsilatConverter.INSTANCE.convertTahsilatToTahsilatDto(byKullaniciAndTahsilatTutari);
        List<BorcDto> allByid = borcService.findAllByid(tahsilatDto.getBorcId());
        double v = 0;
        for (BorcDto dto : allByid) {
            v = dto.getKalanBorcTutari() - dto.getAnaBorcTutari();
        }
        tahsilatDto.setGecikmeZammi(v);
        return tahsilatDto;
    }


}
