package com.example.apibanco.domain.validations;

import com.example.apibanco.api.exception.BusinessException;
import com.example.apibanco.domain.repository.ClientRepository;
import com.example.apibanco.domain.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

@Component
@AllArgsConstructor
public class ManagerValidations {

    private ManagerRepository managerRepository;
    private ClientRepository clientRepository;
    private final MessageSource messageSource;

    public void checkInactiveManager(HashMap<String, String> camposInvalidos, Long gerente_id, Long novoGerente_id){
        checkExistsManager(camposInvalidos, gerente_id);
        checkExistsNewManager(camposInvalidos, novoGerente_id);

        if(!managerRepository.getReferenceById(gerente_id).getActive())
            camposInvalidos.put("/idGerente", messageSource.getMessage("gerente.inactive", null, Locale.US));
        if(!managerRepository.getReferenceById(novoGerente_id).getActive())
            camposInvalidos.put("/idNovoGerente", messageSource.getMessage("gerente.inactive", null, Locale.US));
        if(gerente_id.equals(novoGerente_id))
            camposInvalidos.put("/idGerente", messageSource.getMessage("gerentes.equals", null, Locale.US));
        if (!camposInvalidos.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), camposInvalidos);
    }

    public void checkActiveManager(HashMap<String, String> camposInvalidos, Long gerente_id){
        checkExistsManager(camposInvalidos, gerente_id);

        if(managerRepository.getReferenceById(gerente_id).getActive())
            camposInvalidos.put("/idGerente", messageSource.getMessage("gerente.active", null, Locale.US));
        if (!camposInvalidos.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), camposInvalidos);
    }

    public void checkManagerClientRelationship(HashMap<String, String> camposInvalidos, Long gerente_id, Long cliente_id){
        checkExistsManager(camposInvalidos, gerente_id);

        if(!managerRepository.getReferenceById(gerente_id).getActive())
            camposInvalidos.put("/idGerente", messageSource.getMessage("gerente.inactive", null, Locale.US));
        if(!Objects.equals(clientRepository.getReferenceById(cliente_id).getManager().getId(), gerente_id))
            camposInvalidos.put("/idCliente", messageSource.getMessage("gerente.cliente.relationship", null, Locale.US));
        if (!camposInvalidos.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), camposInvalidos);
    }

    public void checkExistsManager(HashMap<String, String> camposInvalidos, Long gerente_id){
        if (managerRepository.findById(gerente_id).isEmpty())
            camposInvalidos.put("/idGerente", messageSource.getMessage("gerente.not.exist", null, Locale.US));
        if (!camposInvalidos.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), camposInvalidos);
    }

    private void checkExistsNewManager(HashMap<String, String> camposInvalidos, Long novoGerente_id){
        if (managerRepository.findById(novoGerente_id).isEmpty())
            camposInvalidos.put("/idNovoGerente", messageSource.getMessage("gerente.not.exist", null, Locale.US));
        if (!camposInvalidos.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), camposInvalidos);
    }

    public void checkInvalidFields(HashMap<String, String> camposInvalidos, String cpf, String email) {
        if (managerRepository.existsByCpf(cpf))
            camposInvalidos.put("cpf", messageSource.getMessage("cpf.registered", null, Locale.US));
        if (managerRepository.existsByEmail(email))
            camposInvalidos.put("e-mail", messageSource.getMessage("email.registered", null, Locale.US));
        if (!camposInvalidos.isEmpty())
            throw new BusinessException(messageSource.getMessage("general.error", null, Locale.US), camposInvalidos);
    }
}
