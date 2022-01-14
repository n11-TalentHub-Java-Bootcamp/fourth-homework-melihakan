package com.example.debt.converter;

import com.example.debt.dto.BorcDto;
import com.example.debt.entity.Borc;
import com.example.debt.service.entityservice.BorcEntityService;
import lombok.AllArgsConstructor;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BorcConverter {

    BorcConverter INSTANCE = Mappers.getMapper(BorcConverter.class);

    List<BorcDto> convertBorcToBorcDto(List<Borc> borc);


    @Mapping(source = "kullaniciId", target = "kullanici.id")
    Borc convertBorcDtoToBorc(BorcDto borcDto);


    @Mapping(target = "kullaniciId", source = "kullanici.id")
    BorcDto converBorcToBorcDto(Borc borc);

    //@Query("from Borc b where b.kullanici.id = :kullaniciId")
    @Mapping(target = "kullaniciId", source = "kullanici.id")
    List<BorcDto> convertAllBorcListToBorcDtoList(List<Borc> borcList);


}
