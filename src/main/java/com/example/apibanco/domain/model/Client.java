package com.example.apibanco.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cpf;

    private String phone;

    private String email;

    @JoinColumn(name = "client_ative")
    private Boolean active;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_adress_id")
    private ClientAdress adress;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
}


