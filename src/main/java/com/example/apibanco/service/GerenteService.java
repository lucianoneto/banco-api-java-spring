package com.example.apibanco.service;

import com.example.apibanco.model.Gerente;
import com.example.apibanco.repository.GerenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class GerenteService {

    private GerenteRepository gerenteRepository;

    @Transactional
    public Gerente salvarGerente(Gerente gerente) {
        if (gerente.getNome() != null && gerente.getCPF() != null)
            return gerenteRepository.save(gerente);
        return null;
    }

    public boolean verificaGerente(Long gerente_id) {
        return gerenteRepository.findById(gerente_id).isPresent();
    }


}
