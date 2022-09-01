package com.example.apibanco.domain.model.transactions;

import com.example.apibanco.domain.model.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Time;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transfer_value")
    @Min(1)
    private Float value;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "transfer_time")
    private Time time;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "transfer_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "origin_account_id")
    private Account originAccount;

    @ManyToOne
    @JoinColumn(name = "destiny_account_id")
    private Account destinyAccount;
}
