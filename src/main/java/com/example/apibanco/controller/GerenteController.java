package com.example.apibanco.controller;

import com.example.apibanco.model.ClienteEndereco;
import com.example.apibanco.model.ClienteInput;
import com.example.apibanco.service.ClienteService;
import com.example.apibanco.model.Cliente;
import com.example.apibanco.model.Gerente;
import com.example.apibanco.service.GerenteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/gerente")
public class GerenteController {
    private GerenteService gerenteService;
    private ClienteService clienteService;

    @Transactional
    @PostMapping
    public ResponseEntity<Gerente> adicionarGerente(@RequestBody Gerente gerente) {
        if (gerenteService.salvarGerente(gerente) != null)
            return new ResponseEntity<>(gerente, HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/clientes")
    public List<Cliente> listarCliente() {
        return clienteService.mostrarClientes();
    }

    @Transactional
    @PostMapping("/addCliente/{gerente_id}")
    public ResponseEntity<Cliente> adicionarCliente(@RequestBody ClienteInput clienteInput, @PathVariable Long gerente_id) {
        ClienteEndereco clienteEndereco = clienteInput.getClienteEndereco();
        Cliente cliente = clienteInput.getCliente();
        if (gerenteService.verificaGerente(gerente_id) && clienteService.salvarCliente(cliente, gerente_id, clienteEndereco) != null)
            return new ResponseEntity<>(cliente, HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
