package com.example.apibanco.api.controller;

import com.example.apibanco.api.model.ClientInput;
import com.example.apibanco.api.model.ClientPatchInput;
import com.example.apibanco.domain.model.Client;
import com.example.apibanco.domain.model.Account;
import com.example.apibanco.domain.model.Manager;
import com.example.apibanco.domain.service.ClientService;
import com.example.apibanco.domain.service.AccountService;
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

    @GetMapping("/{manager_id}/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> listClientByManager(@PathVariable Long manager_id) {
        return accountService.showAccountsByManager(manager_id);
    }

    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> listClient() {
        return accountService.showAllAccounts();
    }

    @Transactional
    @PostMapping("/{manager_id}/addClient")
    @ResponseStatus(HttpStatus.CREATED)
    public Client addClient(@Valid @RequestBody ClientInput clientInput, @PathVariable Long manager_id) {
        return clientService.saveClient(clientInput, manager_id);
    }

    @Transactional
    @PatchMapping("/{manager_id}/attClient/{client_id}")
    @ResponseStatus(HttpStatus.OK)
    public Client updateClient(@Valid @RequestBody ClientPatchInput clientPatchInput, @PathVariable Long manager_id, @PathVariable Long client_id){
        return clientService.updateClient(clientPatchInput, manager_id, client_id);
    }

    @Transactional
    @PatchMapping("/{manager_id}/inactivateClient/{client_id}")
    @ResponseStatus(HttpStatus.OK)
    public Client inactivateClient(@PathVariable Long manager_id, @PathVariable Long client_id){
        return clientService.inactivateClient(manager_id, client_id);
    }

    @Transactional
    @PatchMapping("/{manager_id}/activateClient/{client_id}")
    @ResponseStatus(HttpStatus.OK)
    public Client activateClient(@PathVariable Long manager_id, @PathVariable Long client_id){
        return clientService.activateClient(manager_id, client_id);
    }

    @Transactional
    @PatchMapping("{manager_id}/inactivate/{newManager_id}")
    @ResponseStatus(HttpStatus.OK)
    public Manager inactivateManager(@PathVariable Long manager_id, @PathVariable Long newManager_id){
        return managerService.inactivateManager(manager_id, newManager_id);
    }

    @Transactional
    @PatchMapping("/{manager_id}/activate")
    @ResponseStatus(HttpStatus.OK)
    public Manager activateManager(@PathVariable Long manager_id){
        return managerService.activateManager(manager_id);
    }
}

