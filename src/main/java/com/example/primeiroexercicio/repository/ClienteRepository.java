package com.example.primeiroexercicio.repository;

import com.example.primeiroexercicio.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {

   Optional<Cliente> findById (Long id);

}
