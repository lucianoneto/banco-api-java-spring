package com.example.apibanco.domain.utils;

import com.example.apibanco.api.exception.NegocioException;
import com.example.apibanco.domain.repository.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@AllArgsConstructor
public class TransactionsUtils {
    private ContaRepository contaRepository;

    public void verificaTransacao(HashMap<String, String> camposInvalidos, Float valor, Long cliente_id) {
        if (contaRepository.findById(cliente_id).isEmpty())
            camposInvalidos.put("/idCliente", "Cliente does not exist in the database");
        if (valor < 1)
            camposInvalidos.put("valor", "Invalid amount.");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }

    public void verificaSaque(HashMap<String, String> camposInvalidos, Float valor, Long cliente_id) {
        verificaTransacao(camposInvalidos, valor, cliente_id);
        if (contaRepository.getById(cliente_id).getSaldo() < valor)
            camposInvalidos.put("valorSaque", "Invalid value of bank draft.");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }

    public void verificaTransferencia(HashMap<String, String> camposInvalidos, Float valor, Long clienteOrigem_id, Long clienteDestino_id) {
        verificaTransacao(camposInvalidos, valor, clienteOrigem_id);
        if (contaRepository.findById(clienteDestino_id).isEmpty())
            camposInvalidos.put("contaDestino", "Cliente does not exist in the database");
        if (contaRepository.getById(clienteOrigem_id).getSaldo() < valor)
            camposInvalidos.put("valorTransferencia", "Invalid value of bank draft.");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }


}
