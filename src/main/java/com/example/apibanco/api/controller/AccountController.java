package com.example.apibanco.api.controller;

import com.example.apibanco.api.model.StatementInput;
import com.example.apibanco.api.model.TransferSentOutput;
import com.example.apibanco.domain.model.transactions.Deposit;
import com.example.apibanco.domain.model.transactions.Withdraw;
import com.example.apibanco.domain.service.AccountService;
import com.example.apibanco.domain.service.transactions.DepositService;
import com.example.apibanco.domain.service.transactions.TransferService;
import com.example.apibanco.domain.service.transactions.WithdrawService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private AccountService accountService;
    private DepositService depositService;
    private WithdrawService withdrawService;
    private TransferService transferService;

    @GetMapping("/{accountId}/statement")
    @ResponseStatus(HttpStatus.OK)
    public StatementInput bankStatement(@PathVariable Long accountId) {
        return accountService.showBankStatement(accountId);
    }

    @Transactional
    @PostMapping("/{accountId}/deposit")
    @ResponseStatus(HttpStatus.OK)
    public Deposit toDeposit(@Valid @PathVariable Long accountId, @RequestParam float value) {
        return depositService.saveDeposit(accountId, value);
    }

    @Transactional
    @PostMapping("/{accountId}/withdraw")
    @ResponseStatus(HttpStatus.OK)
    public Withdraw toWithdraw(@PathVariable Long accountId, @RequestParam float value) {
        return withdrawService.saveWithdraw(accountId, value);
    }

    @Transactional
    @PostMapping("/{originAccountId}/transfer/{destinyAccountId}")
    @ResponseStatus(HttpStatus.OK)
    public TransferSentOutput toTransfer(@Valid @PathVariable Long originAccountId, @PathVariable Long destinyAccountId, @RequestParam float value) {
        return transferService.saveTransfer(originAccountId, destinyAccountId, value);
    }

}
