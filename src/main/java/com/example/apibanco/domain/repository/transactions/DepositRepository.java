package com.example.apibanco.domain.repository.transactions;

import com.example.apibanco.domain.model.transactions.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
    List<Deposit> getByAccountId(Long id);
}
