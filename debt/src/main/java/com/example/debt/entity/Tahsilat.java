package com.example.debt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAHSILAT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tahsilat {

    @Id
    @GeneratedValue()
    private Long id;
    private String name;


}
