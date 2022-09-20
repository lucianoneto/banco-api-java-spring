package com.example.apibanco.domain.model.transactions;

import com.example.apibanco.domain.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Withdraw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "withdraw_value")
    @Min(1)
    private float value;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "withdraw_time")
    private LocalTime time;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "withdraw_date")
    private LocalDate date;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
