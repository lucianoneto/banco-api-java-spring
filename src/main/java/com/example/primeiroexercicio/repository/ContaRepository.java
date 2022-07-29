package com.example.primeiroexercicio.repository;

import com.example.primeiroexercicio.model.Cliente;
import com.example.primeiroexercicio.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findById (Long id);
}
