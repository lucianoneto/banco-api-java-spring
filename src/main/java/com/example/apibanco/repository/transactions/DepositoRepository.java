package com.example.apibanco.repository.transactions;

import com.example.apibanco.model.transactions.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {
    Deposito getById(Long id);
    List<Deposito> getByConta_Id(Long id);
}
