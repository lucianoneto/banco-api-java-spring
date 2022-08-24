package com.example.apibanco.domain.repository.transactions;

import com.example.apibanco.domain.model.transactions.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {
    List<Deposito> getByConta_Id(Long id);
}
