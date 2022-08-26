package com.example.apibanco.domain.validations;

import com.example.apibanco.api.exception.NegocioException;
import com.example.apibanco.domain.repository.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@AllArgsConstructor
public class TransactionsValidations {

    private ContaRepository contaRepository;
    private ContaValidations contaValidations;

    public void verificaTransacao(HashMap<String, String> camposInvalidos, Float valor, Long conta_id) {
        contaValidations.verificaContaClienteInativa(camposInvalidos,conta_id);
        if (valor < 1)
            camposInvalidos.put("valor", "Invalid amount.");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }

    public void verificaSaque(HashMap<String, String> camposInvalidos, Float valor, Long conta_id) {
        verificaTransacao(camposInvalidos, valor, conta_id);
        if (contaRepository.getReferenceById(conta_id).getSaldo() < valor)
            camposInvalidos.put("valorSaque", "Invalid value of bank draft.");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }

    public void verificaTransferencia(HashMap<String, String> camposInvalidos, Float valor, Long contaOrigem_id, Long contaDestino_id) {
        verificaTransacao(camposInvalidos, valor, contaOrigem_id);
        contaValidations.verificaContaClienteInativa(camposInvalidos, contaDestino_id);
        if (contaRepository.getReferenceById(contaOrigem_id).getSaldo() < valor)
            camposInvalidos.put("valorTransferencia", "Invalid value of bank draft.");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }

}
