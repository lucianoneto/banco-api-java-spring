package com.example.apibanco.api.controller;

import com.example.apibanco.domain.model.Cliente;
import com.example.apibanco.domain.model.Gerente;
import com.example.apibanco.api.model.ClienteInput;
import com.example.apibanco.domain.service.ClienteService;
import com.example.apibanco.domain.service.GerenteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/gerente")
public class GerenteController {
    private GerenteService gerenteService;
    private ClienteService clienteService;


    @Transactional
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Gerente adicionarGerente(@Valid @RequestBody Gerente gerente) {
        return gerenteService.salvarGerente(gerente);
    }

    @GetMapping("/clientes")
    public List<Cliente> listarCliente() {
        return clienteService.mostrarClientes();
    }

    @Transactional
    @PostMapping("/addCliente/{gerente_id}")
    public ResponseEntity<Cliente> adicionarCliente(@Valid @RequestBody ClienteInput clienteInput, @PathVariable Long gerente_id) {
        if (gerenteService.verificaGerente(gerente_id))
            return new ResponseEntity<>(clienteService.salvarCliente(clienteInput, gerente_id), HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
