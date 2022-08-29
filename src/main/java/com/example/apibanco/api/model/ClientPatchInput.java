package com.example.apibanco.api.model;

import com.example.apibanco.domain.model.ClientAddress;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class ClientPatchInput {

    @Size(min = 11, max = 20)
    private String phone;

    @Email
    @Size(max = 255)
    private String email;

    private ClientAddress address;
}
