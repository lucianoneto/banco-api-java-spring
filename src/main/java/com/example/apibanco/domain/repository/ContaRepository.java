package com.example.apibanco.domain.repository;

import com.example.apibanco.domain.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findById(Long id);

    Conta getById(Long id);
}
