package com.example.apibanco.domain.model.transactions;

import com.example.apibanco.domain.model.Conta;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.sql.Date;
import java.sql.Time;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transferencia_valor")
    @Min(1)
    private Float valor;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "transferencia_horario")
    private Time horario;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "transferencia_data")
    private Date data;

    @ManyToOne
    @JoinColumn(name = "conta_origem_id")
    private Conta contaOrigem;

    @ManyToOne
    @JoinColumn(name = "conta_destino_id")
    private Conta contaDestino;
}
