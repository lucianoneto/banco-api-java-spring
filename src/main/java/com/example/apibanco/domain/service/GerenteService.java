package com.example.apibanco.domain.service;

import com.example.apibanco.api.exception.NegocioException;
import com.example.apibanco.domain.model.Cliente;
import com.example.apibanco.domain.model.Gerente;
import com.example.apibanco.domain.repository.ClienteRepository;
import com.example.apibanco.domain.repository.GerenteRepository;
import com.example.apibanco.domain.validations.GerenteValidations;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;


@Service
@AllArgsConstructor
public class GerenteService {

    private GerenteRepository gerenteRepository;
    private GerenteValidations gerenteValidations;
    private ClienteRepository clienteRepository;

    @Transactional
    public Gerente salvarGerente(Gerente gerente) {
        HashMap<String, String> camposInvalidos = new HashMap<>();

        verificaCamposInvalidos(camposInvalidos, gerente.getCpf(), gerente.getEmail());
        gerente.setAtivo(true);
        return gerenteRepository.save(gerente);
    }

    public Gerente inativarGerente(Long gerente_id, Long novoGerente_id){
        HashMap<String, String> camposInvalidos = new HashMap<>();
        gerenteValidations.verificaGerenteInativo(camposInvalidos, gerente_id, novoGerente_id);

        gerenteRepository.getReferenceById(gerente_id).setAtivo(false);

        Gerente novoGerente = gerenteRepository.getReferenceById(novoGerente_id);

        List<Cliente> clientesVinculados = clienteRepository.findClientesByGerente_Id(gerente_id);

        if (!clientesVinculados.isEmpty()) {
            clientesVinculados.forEach(cliente -> cliente.setGerente(novoGerente));
            clienteRepository.saveAll(clientesVinculados);
        }
        return gerenteRepository.save(gerenteRepository.getReferenceById(gerente_id));
    }

    public Gerente ativarGerente(Long gerente_id){
        HashMap<String, String> camposInvalidos = new HashMap<>();
        gerenteValidations.verificaGerenteAtivo(camposInvalidos, gerente_id);
        gerenteRepository.getReferenceById(gerente_id).setAtivo(true);

        return gerenteRepository.save(gerenteRepository.getReferenceById(gerente_id));
    }

    private void verificaCamposInvalidos(HashMap<String, String> camposInvalidos, String cpf, String email) {
        if (gerenteRepository.existsByCpf(cpf))
            camposInvalidos.put("cpf", "CPF already registered in the database.");
        if (gerenteRepository.existsByEmail(email))
            camposInvalidos.put("e-mail", "E-mail already registered in the database.");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }


}
