package com.example.debt.entity;

import com.example.debt.gen.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KULLANICI")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kullanici implements BaseEntity {

    @Id
    @GeneratedValue()
    private Long id;
    private String name;


}
