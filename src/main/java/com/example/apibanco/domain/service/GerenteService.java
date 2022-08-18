package com.example.apibanco.domain.service;

import com.example.apibanco.api.exception.NegocioException;
import com.example.apibanco.domain.model.Gerente;
import com.example.apibanco.domain.repository.ClienteRepository;
import com.example.apibanco.domain.repository.GerenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;


@Service
@AllArgsConstructor
public class GerenteService {
    private GerenteRepository gerenteRepository;
    private ClienteRepository clienteRepository;

    @Transactional
    public Gerente salvarGerente(Gerente gerente) {
        HashMap<String, String> camposInvalidos = new HashMap<>();

        verificaCamposInvalidos(camposInvalidos, gerente.getCpf(), gerente.getEmail());
        return gerenteRepository.save(gerente);
    }

    public boolean verificaGerente(Long gerente_id) {
        return gerenteRepository.findById(gerente_id).isPresent();
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
