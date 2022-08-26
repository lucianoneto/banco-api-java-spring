package com.example.apibanco.domain.validations;

import com.example.apibanco.api.exception.NegocioException;
import com.example.apibanco.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@AllArgsConstructor
public class ClienteValidations {
    ClienteRepository clienteRepository;
    GerenteValidations gerenteValidations;
    public void verificaCamposInvalidos(HashMap<String, String> camposInvalidos, Long gerente_id, String cpf, String email) {
        gerenteValidations.verificaGerenteExiste(camposInvalidos, gerente_id);

        if (clienteRepository.existsByCpf(cpf))
            camposInvalidos.put("cpf", "CPF already registered in the database.");
        if (clienteRepository.existsByEmail(email))
            camposInvalidos.put("e-mail", "E-mail already registered in the database.");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }

    public void verificaCamposInvalidos(HashMap<String, String> camposInvalidos, Long gerente_id, String email) {
        gerenteValidations.verificaGerenteExiste(camposInvalidos, gerente_id);

        if (clienteRepository.existsByEmail(email))
            camposInvalidos.put("e-mail", "E-mail already registered in the database.");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }
}
