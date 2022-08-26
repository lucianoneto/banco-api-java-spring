package com.example.apibanco.domain.validations;

import com.example.apibanco.api.exception.NegocioException;
import com.example.apibanco.domain.repository.ClienteRepository;
import com.example.apibanco.domain.repository.GerenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Objects;

@Component
@AllArgsConstructor
public class GerenteValidations {

    private GerenteRepository gerenteRepository;
    private ClienteRepository clienteRepository;

    public void verificaGerenteInativo(HashMap<String, String> camposInvalidos, Long gerente_id, Long novoGerente_id){
        verificaGerenteExiste(camposInvalidos, gerente_id);
        verificaNovoGerenteExiste(camposInvalidos, novoGerente_id);

        if(!gerenteRepository.getReferenceById(gerente_id).getAtivo())
            camposInvalidos.put("/idGerente", "Gerente is inactive");
        if(!gerenteRepository.getReferenceById(novoGerente_id).getAtivo())
            camposInvalidos.put("/idNovoGerente", "Gerente is inactive");
        if(gerente_id.equals(novoGerente_id))
            camposInvalidos.put("/idGerente", "Gerente and novoGerente are equals");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }

    public void verificaGerenteAtivo(HashMap<String, String> camposInvalidos, Long gerente_id){
        verificaGerenteExiste(camposInvalidos, gerente_id);

        if(gerenteRepository.getReferenceById(gerente_id).getAtivo())
            camposInvalidos.put("/idGerente", "Gerente is active");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }

    public void verificaVinculoGerenteCliente(HashMap<String, String> camposInvalidos, Long gerente_id, Long cliente_id){
        verificaGerenteExiste(camposInvalidos, gerente_id);

        if(!gerenteRepository.getReferenceById(gerente_id).getAtivo())
            camposInvalidos.put("/idGerente", "Gerente is inactive");
        if(!Objects.equals(clienteRepository.getReferenceById(cliente_id).getGerente().getId(), gerente_id))
            camposInvalidos.put("/idCliente", "Cliente has no relationship with this Gerente");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }

    public void verificaGerenteExiste(HashMap<String, String> camposInvalidos, Long gerente_id){
        if (gerenteRepository.findById(gerente_id).isEmpty())
            camposInvalidos.put("/idGerente", "Gerente does not exist in the database");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }

    private void verificaNovoGerenteExiste(HashMap<String, String> camposInvalidos, Long novoGerente_id){
        if (gerenteRepository.findById(novoGerente_id).isEmpty())
            camposInvalidos.put("/idNovoGerente", "Gerente does not exist in the database");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }

    public void verificaCamposInvalidos(HashMap<String, String> camposInvalidos, String cpf, String email) {
        if (gerenteRepository.existsByCpf(cpf))
            camposInvalidos.put("cpf", "CPF already registered in the database.");
        if (gerenteRepository.existsByEmail(email))
            camposInvalidos.put("e-mail", "E-mail already registered in the database.");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }
}
