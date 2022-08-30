package com.example.apibanco.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Float balance;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date creationDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Time creationTime;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

}
