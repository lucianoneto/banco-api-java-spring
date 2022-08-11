package com.example.apibanco.controller;

import com.example.apibanco.model.transactions.Transferencia;
import com.example.apibanco.service.ContaService;
import com.example.apibanco.service.transactions.TransferenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/conta")
public class TransferenciaController {

    private ContaService contaService;

    private TransferenciaService transferenciaService;

    @Transactional
    @PostMapping("/{idOrigem}")
    public ResponseEntity<String> transferencia(@PathVariable Long idOrigem, @RequestBody Transferencia transferencia) {
        if (contaService.verificaObjeto(idOrigem) && contaService.verificaObjeto(transferencia.getContaDestino().getId()))
            return new ResponseEntity<>(transferenciaService.salvarTransferencia(idOrigem, transferencia.getContaDestino().getId(), transferencia.getValor()), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
