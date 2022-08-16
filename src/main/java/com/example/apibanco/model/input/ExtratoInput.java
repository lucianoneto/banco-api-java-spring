package com.example.apibanco.model.input;

import com.example.apibanco.model.Conta;
import com.example.apibanco.model.transactions.Deposito;
import com.example.apibanco.model.transactions.Saque;
import com.example.apibanco.model.transactions.TransferenciaEnviada;
import com.example.apibanco.model.transactions.TransferenciaRecebida;
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

    private List<TransferenciaEnviada> transferenciasEnviadas;

    private List<TransferenciaRecebida> transferenciasRecebidas;


}
