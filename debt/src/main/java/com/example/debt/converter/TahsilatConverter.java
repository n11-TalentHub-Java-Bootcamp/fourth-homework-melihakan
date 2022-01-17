package com.example.debt.converter;


import com.example.debt.dto.TahsilatDto;
import com.example.debt.entity.Tahsilat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TahsilatConverter {
    TahsilatConverter INSTANCE = Mappers.getMapper(TahsilatConverter.class);


    @Mapping(target = "borcId", source = "borc.id")
    @Mapping(target = "kullaniciId",source = "kullanici.id")
    TahsilatDto convertTahsilatToTahsilatDto(Tahsilat tahsilat);

    @Mapping( source = "borcId", target = "borc.id")
    @Mapping(source = "kullaniciId",target = "kullanici.id")
    Tahsilat convertTahsilatDtoToTahsilat(TahsilatDto tahsilatDto);

    @Mapping(target = "borcVadeTarihi",source = "borc.vadeTarihi")
    @Mapping(target = "borcId", source = "borc.id")
    @Mapping(target = "kullaniciId",source = "kullanici.id")
    List<TahsilatDto> convertAllTahsilatListToTahsilatDtoList(List<Tahsilat> tahsilatList);

}
