package com.example.apibanco.model.input;

import com.example.apibanco.model.Conta;
import com.example.apibanco.model.transactions.Deposito;
import com.example.apibanco.model.transactions.Saque;
import com.example.apibanco.model.transactions.Transferencia;
import lombok.*;

import java.util.List;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExtratoInput {

    private Conta conta;

    private List<Deposito> depositos;

    private List<Saque> saques;

    private List<Transferencia> transferenciasEnviadas;

    private List<Transferencia> transferenciasRecebidas;


}
