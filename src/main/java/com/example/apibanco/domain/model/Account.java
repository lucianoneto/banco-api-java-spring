package com.example.apibanco.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private float balance;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate creationDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalTime creationTime;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

}
