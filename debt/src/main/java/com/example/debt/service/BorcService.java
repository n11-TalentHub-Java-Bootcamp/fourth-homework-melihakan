package com.example.debt.service;

import com.example.debt.converter.BorcConverter;
import com.example.debt.dto.BorcDto;
import com.example.debt.entity.Borc;
import com.example.debt.service.entityservice.BorcEntityService;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BorcService  {

    private BorcEntityService borcEntityService;
    private static LocalDate begin = LocalDate.of(2018,1,1);


    public ResponseEntity save(BorcDto borcDto) {
        Borc borc = BorcConverter.INSTANCE.convertBorcDtoToBorc(borcDto);
        Borc save = borcEntityService.save(borc);
        return ResponseEntity.ok(save);

    }
    public List<BorcDto> findByAll(){

        List<Borc> all = borcEntityService.findAll();
        List<BorcDto> borcDtoList = BorcConverter.INSTANCE.convertBorcToBorcDto(all);

        //List<BorcDto> collect = borcDtoList.stream().filter(borcDto -> borcDto.getAnaBorcTutari() > 0).collect(Collectors.toList());

        return borcDtoList;
    }

    public List<BorcDto> findAllBorcByKullaniciId(Long kullaniciId) {

        List<Borc> borcList = borcEntityService.findByKullaniciIdOrderByAnaBorcTutari(kullaniciId);
        List<BorcDto> borcDtos = BorcConverter.INSTANCE.convertAllBorcListToBorcDtoList(borcList);

        return borcDtos;
    }
    public List<BorcDto> findAllVadeBorcByKullanaciId(Long kullaniciId){

        List<Borc> borcList = borcEntityService.findAllByKullaniciIdOrderByKalanBorcTutari(kullaniciId);
        List<BorcDto> borcDtos = BorcConverter.INSTANCE.convertAllBorcListToBorcDtoList(borcList);

        for (BorcDto borcDto : borcDtos) {
            LocalDate now = LocalDate.now();
            LocalDate vadeTarihi = borcDto.getVadeTarihi();
            long diff = ChronoUnit.DAYS.between(vadeTarihi, now);

            if(now.isAfter(vadeTarihi)){

                double v = borcDto.getKalanBorcTutari() + (diff * 1.5);
                //v+=v;
                borcDto.setKalanBorcTutari(v);
            }
        }


        return borcDtos;
    }
    public List<BorcDto> findAnaBorcByKullaniciId(Long kullaniciId){

        List<Borc> borc = borcEntityService.findByKullaniciIdOrderByAnaBorcTutari(kullaniciId);
        List<BorcDto> borcDto = BorcConverter.INSTANCE.convertAllBorcListToBorcDtoList(borc);
/*        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("anaBorcTutari");

        SimpleFilterProvider filters = new SimpleFilterProvider().addFilter("BorcFilter", filter);


        MappingJacksonValue mapping = new MappingJacksonValue(borcDto);

        mapping.setFilters(filters);*/

        return borcDto;

    }

    public List<BorcDto> findByOlusmaTarihiGreaterThanEqualAndOlusmaTarihiLessThanEqual(LocalDate startDate,LocalDate endDate){
        List<Borc> borc = borcEntityService.findByOlusmaTarihiGreaterThanEqualAndOlusmaTarihiLessThanEqual(startDate,endDate);
        List<BorcDto> borcDto = BorcConverter.INSTANCE.convertAllBorcListToBorcDtoList(borc);

        return borcDto;
    }
    public BorcDto findByKullaniciIdOrderByKalanBorcTutari(Long kullaniciId){
        List<Borc> borcList = borcEntityService.findAllByKullaniciIdOrderByKalanBorcTutari(kullaniciId);
        List<BorcDto> borcDtos = BorcConverter.INSTANCE.convertAllBorcListToBorcDtoList(borcList);
        Borc borc = borcEntityService.findByKullaniciIdOrderByKalanBorcTutari(kullaniciId);
        BorcDto borcDtoo = BorcConverter.INSTANCE.converBorcToBorcDto(borc);
        double v =0;
        for (BorcDto borcDto : borcDtos) {
            LocalDate now = LocalDate.now();
            LocalDate vadeTarihi = borcDto.getVadeTarihi();
            long diff = ChronoUnit.DAYS.between(vadeTarihi, now);

            if (now.isAfter(vadeTarihi)) {

                v = borcDto.getKalanBorcTutari() + (diff * 1.5);
                //v+=v;
                borcDto.setKalanBorcTutari(v);
            }
        }
        borcDtoo.setKalanBorcTutari(v);

        return borcDtoo;
    }
    public BorcDto findByKullaniciIdOrderByKalanBorcTutarii(Long kullaniciId){
        List<Borc> borcList = borcEntityService.findAllByKullaniciIdOrderByKalanBorcTutari(kullaniciId);
        List<BorcDto> borcDtos = BorcConverter.INSTANCE.convertAllBorcListToBorcDtoList(borcList);
        Borc borc = borcEntityService.findByKullaniciIdOrderByKalanBorcTutari(kullaniciId);
        BorcDto borcDtoo = BorcConverter.INSTANCE.converBorcToBorcDto(borc);
        double v =0;
        for (BorcDto borcDto : borcDtos) {
            LocalDate now = LocalDate.now();
            LocalDate vadeTarihi = borcDto.getVadeTarihi();
            long diff = ChronoUnit.DAYS.between(vadeTarihi, now);

            if (now.isAfter(vadeTarihi)) {

                v = diff * 1.5;
                //v+=v;
                borcDto.setKalanBorcTutari(v);
            }
        }
        borcDtoo.setKalanBorcTutari(v);

        return borcDtoo;
    }


}
