package com.example.debt.service;

import com.example.debt.converter.BorcConverter;
import com.example.debt.dto.BorcDto;
import com.example.debt.entity.Borc;
import com.example.debt.entity.Kullanici;
import com.example.debt.enumborc.EnumBorcTipi;
import com.example.debt.service.entityservice.BorcEntityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        borc.setEnumBorcTipi(EnumBorcTipi.ENUM_NORMAL_BORC);
        Borc save = borcEntityService.save(borc);
        return ResponseEntity.ok(save);

    }
    public Borc saveGecikmeBorc(BorcDto borcDto) {
        Borc borc = BorcConverter.INSTANCE.convertBorcDtoToBorc(borcDto);
        borc.setEnumBorcTipi(EnumBorcTipi.ENUM_GECIKME_ZAMMI);
        Borc save = borcEntityService.save(borc);
        return save;

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
/*    public BorcDto findByKullaniciIdOrderByKalanBorcTutari(Long kullaniciId){
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
    }*/
    public List<BorcDto> findByKullaniciIdOrderByKalanBorcTutari(Long kullaniciId){

        List<Borc> borcList = borcEntityService.findByKullaniciIdOrderByKalanBorcTutari(kullaniciId);
        List<BorcDto> borcDtos = BorcConverter.INSTANCE.convertAllBorcListToBorcDtoList(borcList);
/*        Borc borc = borcEntityService.findByKullaniciIdOrderByKalanBorcTutari(kullaniciId);
        BorcDto borcDtoo = BorcConverter.INSTANCE.converBorcToBorcDto(borc);*/
        double v =0;
        List<Double> sumList = new ArrayList<>();
        double sum =0;
        for (BorcDto borcDto : borcDtos) {

            LocalDate now = LocalDate.now();
            LocalDate vadeTarihi = borcDto.getVadeTarihi();
            long diff = ChronoUnit.DAYS.between(vadeTarihi, now);

            if (now.isAfter(vadeTarihi)) {

                v = borcDto.getKalanBorcTutari()+diff * 1.5;

                borcDto.setKalanBorcTutari(v);
            }
            sumList.add(borcDto.getKalanBorcTutari());
        }

        for (Double aDouble : sumList) {
            sum +=aDouble;
        }


        return borcDtos;
    }
    public Double findByKullaniciIdAndKalanBorcTutari(Long kullaniciId){
        List<Borc> borcList = borcEntityService.findByKullaniciIdOrderByKalanBorcTutari(kullaniciId);
        List<BorcDto> borcDtos = BorcConverter.INSTANCE.convertAllBorcListToBorcDtoList(borcList);
/*        Borc borc = borcEntityService.findByKullaniciIdOrderByKalanBorcTutari(kullaniciId);
        BorcDto borcDtoo = BorcConverter.INSTANCE.converBorcToBorcDto(borc);*/
        double v =0;
        List<Double> sumList = new ArrayList<>();
        double sum =0;
        for (BorcDto borcDto : borcDtos) {

            LocalDate now = LocalDate.now();
            LocalDate vadeTarihi = borcDto.getVadeTarihi();
            long diff = ChronoUnit.DAYS.between(vadeTarihi, now);

            if (now.isAfter(vadeTarihi)) {

                v = borcDto.getKalanBorcTutari()+diff * 1.5;

                borcDto.setKalanBorcTutari(v);
            }
            sumList.add(borcDto.getKalanBorcTutari());

        }

        for (Double aDouble : sumList) {
            sum +=aDouble;
        }
        return sum;
    }
    public Double findByKullaniciIdAndAnaBorcTutari(Long kullaniciId){
        List<Borc> borcList = borcEntityService.findByKullaniciIdOrderByKalanBorcTutari(kullaniciId);
        List<BorcDto> borcDtos = BorcConverter.INSTANCE.convertAllBorcListToBorcDtoList(borcList);

        List<Double> sumList = new ArrayList<>();
        double sum =0;
        for (BorcDto borcDto : borcDtos) {

            sumList.add(borcDto.getAnaBorcTutari());

        }

        for (Double aDouble : sumList) {
            sum +=aDouble;
        }
        return sum;
    }
    public Double findByKullaniciIdAndGecikmeZammı(Long kullaniciId){
        List<Borc> borcList = borcEntityService.findByKullaniciIdOrderByKalanBorcTutari(kullaniciId);
        List<BorcDto> borcDtos = BorcConverter.INSTANCE.convertAllBorcListToBorcDtoList(borcList);
        double v =0;
        List<Double> sumList = new ArrayList<>();
        double sum =0;

        for (BorcDto borcDto : borcDtos) {

            LocalDate now = LocalDate.now();
            LocalDate vadeTarihi = borcDto.getVadeTarihi();
            long diff = ChronoUnit.DAYS.between(vadeTarihi, now);

            if (now.isAfter(vadeTarihi)) {

                v = diff * 1.5;
            }
            sumList.add(v);
        }

        for (Double aDouble : sumList) {
            sum +=aDouble;
        }
        return sum;
    }
    public List<BorcDto> findAllByid(Long id){
        List<Borc> borcList = borcEntityService.findAllByid(id);
        List<BorcDto> borcDtos = BorcConverter.INSTANCE.convertAllBorcListToBorcDtoList(borcList);
        double v =0;
        for (BorcDto borcDto : borcDtos) {
            LocalDate now = LocalDate.now();
            LocalDate vadeTarihi = borcDto.getVadeTarihi();
            long diff = ChronoUnit.DAYS.between(vadeTarihi, now);

            if (now.isAfter(vadeTarihi)) {

                v = diff * 1.5;
            }else {
                v = diff * 2;
            }
            borcDto.setKalanBorcTutari(v);
        }
        return borcDtos;
    }
    public BorcDto updateBorc(BorcDto borcDto) {

        Borc borc = BorcConverter.INSTANCE.convertBorcDtoToBorc(borcDto);
        borc = borcEntityService.save(borc);
        borcDto.setKalanBorcTutari(0.0);
        BorcDto dto = BorcConverter.INSTANCE.convertBorcToBorcDto(borc);

        return dto;
    }


}
