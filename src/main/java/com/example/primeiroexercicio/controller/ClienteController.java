package com.example.primeiroexercicio.controller;

import com.example.primeiroexercicio.model.Cliente;
import com.example.primeiroexercicio.model.Conta;
import com.example.primeiroexercicio.repository.ClienteRepository;
import com.example.primeiroexercicio.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaRepository contaRepository;

    @PostMapping
    public ResponseEntity<Cliente> adicionar(@RequestBody Cliente cliente) {
        if (cliente.getCPF() != null && cliente.getEndereco() != null && cliente.getNome() != null && cliente.getTelefone() != null && cliente.getTipoConta() != null) {
            Conta conta = new Conta();
            conta.setSaldo(0F);
            conta.setExtrato("Conta criada em " + LocalDateTime.now().toString() + "\n");
            conta.setCliente(cliente);
            clienteRepository.save(cliente);
            contaRepository.save(conta);
            return new ResponseEntity<>(cliente, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public List<Cliente> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    @GetMapping("/extrato/{cliente_id}")
    public ResponseEntity<Conta> mostrarExtrato(@PathVariable Long cliente_id) {
        if (contaRepository.findById(cliente_id).isPresent())
            return new ResponseEntity<>(contaRepository.findById(cliente_id).get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/deposito/{cliente_id}/{deposito}")
    public ResponseEntity<String> depositar(@PathVariable Long cliente_id, @PathVariable Float deposito) {
        if (contaRepository.findById(cliente_id).isPresent()) {
            Conta conta = contaRepository.findById(cliente_id).get();
            conta.setSaldo(deposito + conta.getSaldo());
            conta.setExtrato(conta.getExtrato() + "Depósito: " + deposito + "| dia: " + LocalDateTime.now().toString() + "\n");
            contaRepository.save(conta);
            return new ResponseEntity<>("Saldo atual: " + conta.getSaldo(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/saque/{cliente_id}/{saque}")
    public ResponseEntity<String> sacar(@PathVariable Long cliente_id, @PathVariable Float saque) {
        if (contaRepository.findById(cliente_id).isPresent()) {
            Conta conta = contaRepository.findById(cliente_id).get();

            conta.setSaldo(conta.getSaldo() - saque);
            conta.setExtrato(conta.getExtrato() + "Saque: " + saque + "| dia: " + LocalDateTime.now().toString() + "\n");

            contaRepository.save(conta);
            return new ResponseEntity<>("Saldo atual: " + conta.getSaldo(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idTransferidor}/{idRecebedor}/{valor}")
    public ResponseEntity<String> transferencia(@PathVariable Long idRecebedor, @PathVariable Long idTransferidor, @PathVariable Float valor) {
        if (contaRepository.findById(idTransferidor).isPresent() && contaRepository.findById(idRecebedor).isPresent()) {
            Conta contaTransferidor = contaRepository.findById(idTransferidor).get();
            Conta contaRecebedor = contaRepository.findById(idRecebedor).get();

            contaTransferidor.setSaldo(contaTransferidor.getSaldo() - valor);
            contaTransferidor.setExtrato(contaTransferidor.getExtrato() + "Transferência enviada: " + valor + "| dia: " + LocalDateTime.now().toString() + "\n");

            contaRepository.save(contaTransferidor);

            contaRecebedor.setSaldo(valor + contaRecebedor.getSaldo());
            contaRecebedor.setExtrato(contaRecebedor.getExtrato() + "Transferência recebida: " + valor + "| dia: " + LocalDateTime.now().toString() + "\n");

            contaRepository.save(contaRecebedor);
            return new ResponseEntity<>("Saldo atual: " + contaTransferidor.getSaldo(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
