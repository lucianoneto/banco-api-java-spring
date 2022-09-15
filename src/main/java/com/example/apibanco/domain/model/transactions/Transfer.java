package com.example.apibanco.domain.model.transactions;

import com.example.apibanco.domain.model.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transfer_value")
    @Min(1)
    private float value;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "transfer_time")
    private LocalTime time;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "transfer_date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "origin_account_id")
    private Account originAccount;

    @ManyToOne
    @JoinColumn(name = "destiny_account_id")
    private Account destinyAccount;
}
