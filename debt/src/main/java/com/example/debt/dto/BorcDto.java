package com.example.debt.dto;

import lombok.Data;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BorcDto {
    private Long id;
    private LocalDate vadeTarihi;
    private Double anaBorcTutari;
    private Double kalanBorcTutari;
    private Long kullaniciId;
}
