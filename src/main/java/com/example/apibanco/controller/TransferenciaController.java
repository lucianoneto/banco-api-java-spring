package com.example.apibanco.controller;

import com.example.apibanco.service.ContaService;
import com.example.apibanco.service.transactions.TransferenciaService;
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
public class TransferenciaController {

    private ContaService contaService;

    private TransferenciaService transferenciaService;

    @Transactional
    @PostMapping("/{idOrigem}/{idDestino}/{valor}")
    public ResponseEntity<String> transferencia(@PathVariable Long idOrigem, @PathVariable Long idDestino, @PathVariable Float valor) {
        if (contaService.verificaObjeto(idOrigem) && contaService.verificaObjeto(idDestino))
            return new ResponseEntity<>(transferenciaService.salvarTransferencia(idOrigem, idDestino, valor), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
