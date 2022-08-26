package com.example.apibanco.domain.validations;

import com.example.apibanco.api.exception.NegocioException;
import com.example.apibanco.domain.repository.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@AllArgsConstructor
public class ContaValidations {

    private ContaRepository contaRepository;

    public void verificaContaClienteInativa(HashMap<String, String> camposInvalidos, Long conta_id){
        verificaContaClienteExistente(camposInvalidos, conta_id);
        if(!contaRepository.getReferenceById(conta_id).getCliente().getAtivo())
            camposInvalidos.put("/idConta", "Conta is inactive");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }

    public void verificaContaClienteAtiva(HashMap<String, String> camposInvalidos, Long conta_id){
        verificaContaClienteExistente(camposInvalidos, conta_id);
        if(contaRepository.getReferenceById(conta_id).getCliente().getAtivo())
            camposInvalidos.put("/idConta", "Conta is active");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }

    private void verificaContaClienteExistente(HashMap<String, String> camposInvalidos, Long conta_id){
        if (contaRepository.findById(conta_id).isEmpty())
            camposInvalidos.put("/idConta", "Conta does not exist in the database");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }
}
