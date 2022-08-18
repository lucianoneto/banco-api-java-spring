package com.example.apibanco.api.controller;

import com.example.apibanco.api.model.ExtratoInput;
import com.example.apibanco.api.model.TransferenciaEnviadaOutput;
import com.example.apibanco.domain.model.transactions.Deposito;
import com.example.apibanco.domain.model.transactions.Saque;
import com.example.apibanco.domain.model.transactions.Transferencia;
import com.example.apibanco.domain.service.ContaService;
import com.example.apibanco.domain.service.transactions.DepositoService;
import com.example.apibanco.domain.service.transactions.SaqueService;
import com.example.apibanco.domain.service.transactions.TransferenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/conta")
public class ContaController {
    private ContaService contaService;
    private DepositoService depositoService;
    private SaqueService saqueService;
    private TransferenciaService transferenciaService;
    @GetMapping("/extrato/{cliente_id}")
    @ResponseStatus(HttpStatus.OK)
    public ExtratoInput extrato(@PathVariable Long cliente_id) {
        return contaService.mostrarExtrato(cliente_id);
    }

    @Transactional
    @PostMapping("/deposito/{cliente_id}")
    @ResponseStatus(HttpStatus.OK)
    public Deposito depositar(@Valid @PathVariable Long cliente_id, @RequestBody Deposito deposito) {
        return depositoService.salvarDeposito(cliente_id, deposito.getValor());
    }

    @Transactional
    @PostMapping("/saque/{cliente_id}")
    @ResponseStatus(HttpStatus.OK)
    public Saque sacar(@PathVariable Long cliente_id, @RequestBody Saque saque) {
        return saqueService.salvarSaque(cliente_id, saque.getValor());
    }

    @Transactional
    @PostMapping("/transferencia/{clienteOrigem_id}")
    @ResponseStatus(HttpStatus.OK)
    public TransferenciaEnviadaOutput transferencia(@Valid @PathVariable Long clienteOrigem_id, @RequestBody Transferencia transferencia) {
        return transferenciaService.salvarTransferencia(clienteOrigem_id, transferencia);
    }

}
