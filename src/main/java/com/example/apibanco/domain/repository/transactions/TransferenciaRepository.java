package com.example.apibanco.domain.repository.transactions;

import com.example.apibanco.domain.model.transactions.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    Transferencia getById(Long id);

    List<Transferencia> getByContaOrigemId(Long id);

    List<Transferencia> getByContaDestinoId(Long id);
}
