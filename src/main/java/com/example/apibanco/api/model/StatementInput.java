package com.example.apibanco.api.model;

import com.example.apibanco.domain.model.transactions.Deposit;
import com.example.apibanco.domain.model.transactions.Withdraw;
import lombok.*;

import java.util.List;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatementInput {

    private AccountOutput account;

    private List<Deposit> deposits;

    private List<Withdraw> withdraws;

    private List<TransferSentOutput> transferSentOutputs;

    private List<TransferReceivedOutput> transferReceivedOutputs;


}
