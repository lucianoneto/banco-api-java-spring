package com.example.apibanco.api.controller;

import com.example.apibanco.api.model.ClientInput;
import com.example.apibanco.api.model.ClientPatchInput;
import com.example.apibanco.domain.model.Account;
import com.example.apibanco.domain.model.Client;
import com.example.apibanco.domain.model.Manager;
import com.example.apibanco.domain.service.AccountService;
import com.example.apibanco.domain.service.ClientService;
import com.example.apibanco.domain.service.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/managers")
public class ManagerController {

    private ManagerService managerService;
    private ClientService clientService;
    private AccountService accountService;

    @Transactional
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Manager addManager(@Valid @RequestBody Manager manager) {
        return managerService.saveManager(manager);
    }

    @GetMapping("/{managerId}/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> listClientByManager(@PathVariable Long managerId) {
        return accountService.showAccountsByManager(managerId);
    }

    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> listClient() {
        return accountService.showAllAccounts();
    }

    @Transactional
    @PostMapping("/{managerId}/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public Client addClient(@Valid @RequestBody ClientInput clientInput, @PathVariable Long managerId) {
        return clientService.saveClient(clientInput, managerId);
    }

    @Transactional
    @PatchMapping("/{managerId}/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public Client updateClient(@Valid @RequestBody ClientPatchInput clientPatchInput, @PathVariable Long managerId, @PathVariable Long clientId) {
        return clientService.updateClient(clientPatchInput, managerId, clientId);
    }

    @Transactional
    @PatchMapping("/{managerId}/clients/{clientId}/inactivate")
    @ResponseStatus(HttpStatus.OK)
    public Client inactivateClient(@PathVariable Long managerId, @PathVariable Long clientId) {
        return clientService.inactivateClient(managerId, clientId);
    }

    @Transactional
    @PatchMapping("/{managerId}/clients/{clientId}/activate")
    @ResponseStatus(HttpStatus.OK)
    public Client activateClient(@PathVariable Long managerId, @PathVariable Long clientId) {
        return clientService.activateClient(managerId, clientId);
    }

    @Transactional
    @PatchMapping("{managerId}/inactivate/{newManagerId}")
    @ResponseStatus(HttpStatus.OK)
    public Manager inactivateManager(@PathVariable Long managerId, @PathVariable Long newManagerId) {
        return managerService.inactivateManager(managerId, newManagerId);
    }

    @Transactional
    @PatchMapping("/{managerId}/activate")
    @ResponseStatus(HttpStatus.OK)
    public Manager activateManager(@PathVariable Long managerId) {
        return managerService.activateManager(managerId);
    }
}

