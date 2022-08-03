package com.example.apibanco.repository;

import com.example.apibanco.model.transactions.Saque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaqueRepository extends JpaRepository<Saque, Long> {
}
