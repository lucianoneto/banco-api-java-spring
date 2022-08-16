package com.example.apibanco.repository.transactions;

import com.example.apibanco.model.transactions.TransferenciaRecebida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferenciaRecebidaRepository extends JpaRepository<TransferenciaRecebida, Long> {

    List<TransferenciaRecebida> getByContaDestinoId(Long id);

}
