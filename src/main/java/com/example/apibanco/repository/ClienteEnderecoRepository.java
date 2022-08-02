package com.example.apibanco.repository;

import com.example.apibanco.model.ClienteEndereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteEnderecoRepository extends JpaRepository<ClienteEndereco, Long> {
}
