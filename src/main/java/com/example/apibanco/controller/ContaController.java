package com.example.apibanco.controller;

import com.example.apibanco.model.Conta;
import com.example.apibanco.service.ContaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/conta")
public class ContaController {

    ContaService contaService;

    @GetMapping("/extrato/{cliente_id}")
    public ResponseEntity<Conta> extrato(@PathVariable Long cliente_id) {
        if (contaService.verificaObjeto(cliente_id))
            return new ResponseEntity<>(contaService.mostrarExtrato(cliente_id), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Transactional
    @PostMapping("/{idTransferidor}/{idRecebedor}/{valor}")
    public ResponseEntity<String> transferencia(@PathVariable Long idRecebedor, @PathVariable Long idTransferidor, @PathVariable Float valor) {
        if (contaService.verificaObjeto(idRecebedor) && contaService.verificaObjeto(idTransferidor))
            return new ResponseEntity<>(contaService.salvarTransferencia(idRecebedor, idTransferidor, valor), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
