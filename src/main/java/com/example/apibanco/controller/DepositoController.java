package com.example.apibanco.controller;

import com.example.apibanco.model.transactions.Deposito;
import com.example.apibanco.service.ContaService;
import com.example.apibanco.service.DepositoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/conta")
public class DepositoController {

    private ContaService contaService;

    private DepositoService depositoService;

    @Transactional
    @PostMapping("/deposito/{cliente_id}/{deposito}")
    public ResponseEntity<String> depositar(@PathVariable Long cliente_id, @PathVariable Float deposito) {
        if (contaService.verificaObjeto(cliente_id))
            return new ResponseEntity<>(depositoService.salvarDeposito(cliente_id, deposito, new Deposito()), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
