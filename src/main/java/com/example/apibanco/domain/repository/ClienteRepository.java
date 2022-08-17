package com.example.apibanco.domain.repository;

import com.example.apibanco.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findById(Long id);

    boolean existsByCpf(String CPF);

    boolean existsByEmail(String email);
}
