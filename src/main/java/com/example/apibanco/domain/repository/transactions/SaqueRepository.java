package com.example.apibanco.domain.repository.transactions;

import com.example.apibanco.domain.model.transactions.Saque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaqueRepository extends JpaRepository<Saque, Long> {
    List<Saque> getByConta_Id(Long id);


}
