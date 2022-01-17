package com.example.debt.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TahsilatDto {

    private Long id;
    private Double tahsilatTutari;
    private LocalDate olusmaTarihi;
    private Long borcId;
    private Long kullaniciId;
    private LocalDate borcVadeTarihi;
    private Double gecikmeZammi;

}
