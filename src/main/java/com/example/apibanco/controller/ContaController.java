package com.example.apibanco.controller;

import com.example.apibanco.model.input.ExtratoInput;
import com.example.apibanco.service.ContaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/conta")
public class ContaController {

    ContaService contaService;

    @GetMapping("/extrato/{cliente_id}")
    public ResponseEntity<ExtratoInput> extrato(@PathVariable Long cliente_id) {
        if (contaService.verificaObjeto(cliente_id))
            return new ResponseEntity<>(contaService.mostrarExtrato(cliente_id), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
