package com.example.apibanco.domain.repository;

import com.example.apibanco.domain.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findById(Long id);

    List<Conta> findContaByCliente_Gerente_Id (Long id);
}
