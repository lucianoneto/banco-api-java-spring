package com.example.apibanco.api.controller;

import com.example.apibanco.api.model.ExtratoInput;
import com.example.apibanco.api.model.TransferSentOutput;
import com.example.apibanco.domain.model.transactions.Deposit;
import com.example.apibanco.domain.model.transactions.Withdraw;
import com.example.apibanco.domain.model.transactions.Transfer;
import com.example.apibanco.domain.service.AccountService;
import com.example.apibanco.domain.service.transactions.DepositService;
import com.example.apibanco.domain.service.transactions.WithdrawService;
import com.example.apibanco.domain.service.transactions.TransferenciaService;
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
    private TransferenciaService transferenciaService;

    @GetMapping("/{account_id}/statement")
    @ResponseStatus(HttpStatus.OK)
    public ExtratoInput bankStatement(@PathVariable Long account_id) {
        return accountService.showBankStatement(account_id);
    }

    @Transactional
    @PostMapping("/{account_id}/deposits")
    @ResponseStatus(HttpStatus.OK)
    public Deposit toDeposit(@Valid @PathVariable Long account_id, @RequestBody Deposit deposit) {
        return depositService.saveDeposit(account_id, deposit.getValue());
    }

    @Transactional
    @PostMapping("/{account_id}/withdraws")
    @ResponseStatus(HttpStatus.OK)
    public Withdraw toWithdraw(@PathVariable Long account_id, @RequestBody Withdraw saque) {
        return withdrawService.saveWithdraw(account_id, saque.getValue());
    }

    @Transactional
    @PostMapping("/{originAccount_id}/transfers/{destinyAccount_id}")
    @ResponseStatus(HttpStatus.OK)
    public TransferSentOutput toTransfer(@Valid @PathVariable Long originAccount_id, @PathVariable Long destinyAccount_id, @RequestBody Transfer transfer) {
        return transferenciaService.saveTransfer(originAccount_id, destinyAccount_id, transfer.getValue());
    }

}
