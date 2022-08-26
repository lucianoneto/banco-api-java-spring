package com.example.apibanco.domain.repository.transactions;

import com.example.apibanco.domain.model.transactions.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transfer, Long> {

    List<Transfer> getByOriginAccountId(Long id);

    List<Transfer> getByDestinyAccountId(Long id);
}
