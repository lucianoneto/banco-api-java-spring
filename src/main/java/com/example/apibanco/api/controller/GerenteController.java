package com.example.apibanco.api.controller;

import com.example.apibanco.api.model.ClienteInput;
import com.example.apibanco.domain.model.Cliente;
import com.example.apibanco.domain.model.Conta;
import com.example.apibanco.domain.model.Gerente;
import com.example.apibanco.domain.service.ClienteService;
import com.example.apibanco.domain.service.ContaService;
import com.example.apibanco.domain.service.GerenteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/gerentes")
public class GerenteController {
    private GerenteService gerenteService;
    private ClienteService clienteService;
    private ContaService contaService;

    @Transactional
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Gerente adicionarGerente(@Valid @RequestBody Gerente gerente) {
        return gerenteService.salvarGerente(gerente);
    }

    @GetMapping("/{gerente_id}/clientes")
    @ResponseStatus(HttpStatus.OK)
    public List<Conta> listarClientePorGerente(@PathVariable Long gerente_id) {
        return contaService.mostrarContasPorGerente(gerente_id);
    }

    @GetMapping("/clientes")
    @ResponseStatus(HttpStatus.OK)
    public List<Conta> listarCliente() {
        return contaService.mostrarTodasContas();
    }


    @Transactional
    @PostMapping("/{gerente_id}/addCliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionarCliente(@Valid @RequestBody ClienteInput clienteInput, @PathVariable Long gerente_id) {
        return clienteService.salvarCliente(clienteInput, gerente_id);
    }

    @Transactional
    @PatchMapping("/{gerente_id}/inativarCliente/{cliente_id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente inativarCliente(@PathVariable Long gerente_id, @PathVariable Long cliente_id){
        return clienteService.inativarCliente(gerente_id, cliente_id);
    }

    @Transactional
    @PatchMapping("/{gerente_id}/ativarCliente/{cliente_id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente ativarCliente(@PathVariable Long gerente_id, @PathVariable Long cliente_id){
        return clienteService.ativarCliente(gerente_id, cliente_id);
    }

    @Transactional
    @PatchMapping("{gerente_id}/inativar/{novoGerente_id}")
    @ResponseStatus(HttpStatus.OK)
    public Gerente inativarGerente(@PathVariable Long gerente_id, @PathVariable Long novoGerente_id){
        return gerenteService.inativarGerente(gerente_id, novoGerente_id);
    }

    @Transactional
    @PatchMapping("/{gerente_id}/ativar")
    @ResponseStatus(HttpStatus.OK)
    public Gerente ativarGerente(@PathVariable Long gerente_id){
        return gerenteService.ativarGerente(gerente_id);
    }
}

