package com.example.debt.dao;

import com.example.debt.entity.Borc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorcDao extends JpaRepository<Borc,Long>{




}
