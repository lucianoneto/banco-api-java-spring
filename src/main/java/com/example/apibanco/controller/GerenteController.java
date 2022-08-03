package com.example.apibanco.controller;

import com.example.apibanco.model.Cliente;
import com.example.apibanco.model.Gerente;
import com.example.apibanco.model.input.ClienteInput;
import com.example.apibanco.service.ClienteService;
import com.example.apibanco.service.GerenteService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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

    private ModelMapper modelMapper;

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
    public ResponseEntity<ClienteInput> adicionarCliente(@RequestBody ClienteInput clienteInput, @PathVariable Long gerente_id) {
        modelMapper.map(clienteInput, ClienteInput.class);
        if (gerenteService.verificaGerente(gerente_id) && clienteService.salvarCliente(clienteInput, gerente_id) != null)
            return new ResponseEntity<>(clienteInput, HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
