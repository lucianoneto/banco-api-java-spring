package com.example.apibanco.domain.repository;

import com.example.apibanco.domain.model.ClientAdress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAdressRepository extends JpaRepository<ClientAdress, Long> {

}
