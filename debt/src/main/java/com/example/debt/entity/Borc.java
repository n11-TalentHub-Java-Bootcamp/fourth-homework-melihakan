package com.example.debt.entity;

import com.example.debt.enumborc.EnumBorcTipi;
import com.example.debt.gen.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "BORC")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borc implements BaseEntity {

    @Id
    @GeneratedValue()
    private Long id;

    private LocalDate vadeTarihi;
    private LocalDate olusmaTarihi;

    @Column(updatable = false)
    private Double anaBorcTutari;
    private Double kalanBorcTutari;
    private EnumBorcTipi enumBorcTipi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KULLANICI",
            foreignKey = @ForeignKey(name = "FK_KULLANICI_BORC_ID")
    )
    private Kullanici kullanici;



}
