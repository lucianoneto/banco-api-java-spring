package com.example.apibanco.domain.repository;

import com.example.apibanco.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findById(Long id);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    List<Client> findClientsByManager_Id(Long manager_id);

}
