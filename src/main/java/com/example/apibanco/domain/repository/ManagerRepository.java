package com.example.apibanco.domain.repository;

import com.example.apibanco.domain.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findById(Long id);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}
