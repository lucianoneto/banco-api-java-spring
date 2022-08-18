package com.example.apibanco.domain.repository;

import com.example.apibanco.domain.model.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Long> {
    Optional<Gerente> findById(Long id);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}
