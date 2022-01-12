package com.example.debt.dao;

import com.example.debt.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KullaniciDao extends JpaRepository<Kullanici,Long> {
}
