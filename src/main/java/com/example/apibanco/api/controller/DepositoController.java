package com.example.apibanco.api.controller;

import com.example.apibanco.domain.model.transactions.Deposito;
import com.example.apibanco.domain.service.ContaService;
import com.example.apibanco.domain.service.transactions.DepositoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/conta")
public class DepositoController {

    private ContaService contaService;

    private DepositoService depositoService;

    @Transactional
    @PostMapping("/deposito/{cliente_id}")
    public ResponseEntity<Deposito> depositar(@Valid @PathVariable Long cliente_id, @RequestBody Deposito deposito) {
        if (contaService.verificaObjeto(cliente_id))
            return new ResponseEntity<>(depositoService.salvarDeposito(cliente_id, deposito.getValor()), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
