package com.example.apibanco.api.model;

import com.example.apibanco.domain.model.Conta;
import com.example.apibanco.domain.model.transactions.Saque;
import com.example.apibanco.domain.model.transactions.Deposito;
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

    private List<TransferenciaEnviadaOutput> transferenciasEnviadas;

    private List<TransferenciaRecebidaOutput> transferenciasRecebidas;


}
