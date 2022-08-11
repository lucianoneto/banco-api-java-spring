package com.example.apibanco.repository;

import com.example.apibanco.model.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Long> {
    Optional<Gerente> findById(Long id);

    Gerente getById(Long id);

    boolean existsByCpf(String CPF);

    boolean existsByEmail(String email);
}
