package com.example.apibanco.model.transactions;

import com.example.apibanco.model.Conta;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.sql.Date;
import java.sql.Time;

@Table(name = "transferencia")
@Entity
@Getter
@Setter
public class TransferenciaEnviada {
    @Id
    @JsonIgnore
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "conta_origem_id")
    private Conta contaOrigem;

    @ManyToOne
    @JoinColumn(name = "conta_destino_id")
    private Conta contaDestino;
}
