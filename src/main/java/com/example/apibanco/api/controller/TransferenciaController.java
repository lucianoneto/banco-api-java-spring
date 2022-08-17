package com.example.apibanco.api.controller;

import com.example.apibanco.api.model.TransferenciaEnviadaOutput;
import com.example.apibanco.domain.model.transactions.Transferencia;
import com.example.apibanco.domain.service.ContaService;
import com.example.apibanco.domain.service.transactions.TransferenciaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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

    ModelMapper modelMapper;

    @Transactional
    @PostMapping("/{idOrigem}")
    public ResponseEntity<TransferenciaEnviadaOutput> transferencia(@PathVariable Long idOrigem, @RequestBody Transferencia transferencia) {
        if (contaService.verificaObjeto(idOrigem) && contaService.verificaObjeto(transferencia.getContaDestino().getId()))
            return new ResponseEntity<>(modelMapper.map(transferenciaService.salvarTransferencia(idOrigem, transferencia.getContaDestino().getId(), transferencia.getValor()), TransferenciaEnviadaOutput.class), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
