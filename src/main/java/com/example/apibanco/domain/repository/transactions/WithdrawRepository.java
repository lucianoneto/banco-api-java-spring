package com.example.apibanco.domain.repository.transactions;

import com.example.apibanco.domain.model.transactions.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {
    List<Withdraw> getByAccountId(Long id);


}
