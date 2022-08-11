package com.example.apibanco.service;

import com.example.apibanco.exception.NegocioException;
import com.example.apibanco.model.Gerente;
import com.example.apibanco.repository.ClienteRepository;
import com.example.apibanco.repository.GerenteRepository;
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
        camposInvalidos(camposInvalidos, gerente.getCpf(), gerente.getEmail());
        return gerenteRepository.save(gerente);
    }

    public void camposInvalidos(HashMap<String, String> camposInvalidos, String cpf, String email) {
        if (gerenteRepository.existsByCpf(cpf) || clienteRepository.existsByCpf(cpf))
            camposInvalidos.put("cpf", "CPF already registered in the database.");
        if (gerenteRepository.existsByEmail(email) || clienteRepository.existsByEmail(email))
            camposInvalidos.put("e-mail", "E-mail already registered in the database.");
        if (!camposInvalidos.isEmpty())
            throw new NegocioException("One or more fields are invalid.", camposInvalidos);
    }

    public boolean verificaGerente(Long gerente_id) {
        return gerenteRepository.findById(gerente_id).isPresent();
    }


}
