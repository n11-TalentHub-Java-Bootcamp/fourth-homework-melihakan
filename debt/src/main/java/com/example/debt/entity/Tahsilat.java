package com.example.debt.entity;

import com.example.debt.gen.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TAHSILAT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tahsilat implements BaseEntity {

    @Id
    @GeneratedValue()
    private Long id;
    private Double tahsilatTutari;
    private LocalDate olusmaTarihi;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BORC",
            foreignKey = @ForeignKey(name = "FK_BORC_TAHSILAT_ID")
    )
    private Borc borc;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KULLANICI",
            foreignKey = @ForeignKey(name = "FK_KULLANICI_TAHSILAT_ID")
    )
    private Kullanici kullanici;


}
