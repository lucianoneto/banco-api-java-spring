package com.example.apibanco.domain.repository;

import com.example.apibanco.domain.model.ClienteEndereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteEnderecoRepository extends JpaRepository<ClienteEndereco, Long> {
}
