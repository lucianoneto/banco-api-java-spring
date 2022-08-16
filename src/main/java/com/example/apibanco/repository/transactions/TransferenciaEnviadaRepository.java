package com.example.apibanco.repository.transactions;

import com.example.apibanco.model.transactions.TransferenciaEnviada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferenciaEnviadaRepository extends JpaRepository<TransferenciaEnviada, Long> {

    List<TransferenciaEnviada> getByContaOrigemId(Long id);
}
