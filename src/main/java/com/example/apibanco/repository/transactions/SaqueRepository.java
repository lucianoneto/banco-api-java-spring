package com.example.apibanco.repository.transactions;

import com.example.apibanco.model.transactions.Saque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaqueRepository extends JpaRepository<Saque, Long> {
    Saque getById(Long id);

    List<Saque> getByConta_Id(Long id);


}
