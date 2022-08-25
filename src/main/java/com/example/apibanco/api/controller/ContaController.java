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
@RequestMapping("/contas")
public class ContaController {

    private ContaService contaService;
    private DepositoService depositoService;
    private SaqueService saqueService;
    private TransferenciaService transferenciaService;

    @GetMapping("/{conta_id}/extrato")
    @ResponseStatus(HttpStatus.OK)
    public ExtratoInput extrato(@PathVariable Long conta_id) {
        return contaService.mostrarExtrato(conta_id);
    }

    @Transactional
    @PostMapping("/{conta_id}/depositos")
    @ResponseStatus(HttpStatus.OK)
    public Deposito depositar(@Valid @PathVariable Long conta_id, @RequestBody Deposito deposito) {
        return depositoService.salvarDeposito(conta_id, deposito.getValor());
    }

    @Transactional
    @PostMapping("/{conta_id}/saques")
    @ResponseStatus(HttpStatus.OK)
    public Saque sacar(@PathVariable Long conta_id, @RequestBody Saque saque) {
        return saqueService.salvarSaque(conta_id, saque.getValor());
    }

    @Transactional
    @PostMapping("/{contaOrigem_id}/transferencias/{contaDestino_id}")
    @ResponseStatus(HttpStatus.OK)
    public TransferenciaEnviadaOutput transferencia(@Valid @PathVariable Long contaOrigem_id, @PathVariable Long contaDestino_id, @RequestBody Transferencia transferencia) {
        return transferenciaService.salvarTransferencia(contaOrigem_id, contaDestino_id, transferencia.getValor());
    }

}
