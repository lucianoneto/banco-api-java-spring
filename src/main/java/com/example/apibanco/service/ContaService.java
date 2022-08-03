package com.example.apibanco.service;

import com.example.apibanco.model.Conta;
import com.example.apibanco.model.input.ClienteInput;
import com.example.apibanco.repository.ContaRepository;
import com.example.apibanco.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ContaService {
    private ContaRepository contaRepository;

    @Transactional
    public void salvarConta(Conta conta, ClienteInput clienteInput) {
        conta.setSaldo(0F);
        conta.setExtrato("Conta criada em " + Utils.dateTimeNow(LocalDateTime.now()) + "\n");
        conta.setCliente(clienteInput.getCliente());

        contaRepository.save(conta);
    }

    public Conta mostrarExtrato(Long cliente_id) {
        return contaRepository.getById(cliente_id);
    }

    public boolean verificaObjeto(Long cliente_id) {
        return contaRepository.findById(cliente_id).isPresent();
    }


    @Transactional
    public String salvarTransferencia(Long idRecebedor, Long idTransferidor, Float valorTransferencia) {
        Conta contaTransferidor = contaRepository.getById(idTransferidor);
        Conta contaRecebedor = contaRepository.getById(idRecebedor);

        if (contaTransferidor.getSaldo() >= valorTransferencia) {
            contaTransferidor.setSaldo(contaTransferidor.getSaldo() - valorTransferencia);
            contaTransferidor.setExtrato(contaTransferidor.getExtrato() + "Transferência enviada: " + valorTransferencia + "| dia: " + Utils.dateTimeNow(LocalDateTime.now()) + "\n");

            contaRepository.save(contaTransferidor);

            contaRecebedor.setSaldo(valorTransferencia + contaRecebedor.getSaldo());
            contaRecebedor.setExtrato(contaRecebedor.getExtrato() + "Transferência recebida: " + valorTransferencia + "| dia: " + Utils.dateTimeNow(LocalDateTime.now()) + "\n");

            contaRepository.save(contaRecebedor);

            return "Saldo atual: " + contaTransferidor.getSaldo();
        }
        return "Saldo inválido, o valor na conta do transferidor é menor do que o requisitado";
    }
}
