package com.example.apibanco.controller;

import com.example.apibanco.model.transactions.Saque;
import com.example.apibanco.service.ContaService;
import com.example.apibanco.service.transactions.SaqueService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/conta")
public class SaqueController {
    private ContaService contaService;
    private SaqueService saqueService;

    @Transactional
    @PostMapping("/saque/{cliente_id}")
    public ResponseEntity<String> sacar(@PathVariable Long cliente_id, @RequestBody Saque saque) {
        if (contaService.verificaObjeto(cliente_id))
            return new ResponseEntity<>(saqueService.salvarSaque(cliente_id, saque.getValor()), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
