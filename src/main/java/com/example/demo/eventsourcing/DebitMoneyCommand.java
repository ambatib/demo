package com.example.demo.eventsourcing;

public class DebitMoneyCommand extends BaseCommand<String> {

    public final double debitAmount;

    public final String currency;

    public DebitMoneyCommand(String id, double debitAmount, String currency) {
        super(id);
        this.debitAmount = debitAmount;
        this.currency = currency;
    }
}
