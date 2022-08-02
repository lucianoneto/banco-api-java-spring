package com.example.apibanco.service;

import com.example.apibanco.model.Cliente;
import com.example.apibanco.model.Conta;
import com.example.apibanco.repository.ContaRepository;
import com.example.apibanco.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ContaService {
    private ContaRepository contaRepository;

    @Transactional
    public Conta salvarConta(Conta conta, Cliente cliente) {
        conta.setSaldo(0F);
        conta.setExtrato("Conta criada em " + Utils.dateNow(LocalDateTime.now()) + "\n");
        conta.setCliente(cliente);
        return contaRepository.save(conta);
    }

    public Conta mostrarExtrato(Long cliente_id) {
        return contaRepository.getById(cliente_id);
    }

    public boolean verificaObjeto(Long cliente_id) {
        return contaRepository.findById(cliente_id).isPresent();
    }

    @Transactional
    public String salvarDeposito(Long cliente_id, Float deposito) {
        Conta conta = contaRepository.getById(cliente_id);

        conta.setSaldo(deposito + conta.getSaldo());
        conta.setExtrato(conta.getExtrato() + "Depósito: " + deposito + "| dia: " + Utils.dateNow(LocalDateTime.now()) + "\n");

        contaRepository.save(conta);

        return "Saldo atual: " + conta.getSaldo();

    }

    @Transactional
    public String salvarSaque(Long cliente_id, Float saque) {
        Conta conta = contaRepository.getById(cliente_id);

        conta.setSaldo(conta.getSaldo() - saque);
        conta.setExtrato(conta.getExtrato() + "Saque: " + saque + "| dia: " + Utils.dateNow(LocalDateTime.now()) + "\n");

        contaRepository.save(conta);

        return "Saldo atual: " + conta.getSaldo();
    }

    @Transactional
    public String salvarTransferencia(Long idRecebedor, Long idTransferidor, Float valor) {
        Conta contaTransferidor = contaRepository.getById(idTransferidor);
        Conta contaRecebedor = contaRepository.getById(idRecebedor);

        contaTransferidor.setSaldo(contaTransferidor.getSaldo() - valor);
        contaTransferidor.setExtrato(contaTransferidor.getExtrato() + "Transferência enviada: " + valor + "| dia: " + Utils.dateNow(LocalDateTime.now()) + "\n");

        contaRepository.save(contaTransferidor);

        contaRecebedor.setSaldo(valor + contaRecebedor.getSaldo());
        contaRecebedor.setExtrato(contaRecebedor.getExtrato() + "Transferência recebida: " + valor + "| dia: " + Utils.dateNow(LocalDateTime.now()) + "\n");

        contaRepository.save(contaRecebedor);

        return "Saldo atual: " + contaTransferidor.getSaldo();
    }
}
