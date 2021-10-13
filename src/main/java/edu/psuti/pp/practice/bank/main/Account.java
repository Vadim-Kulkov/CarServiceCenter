/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;

import edu.psuti.pp.practice.bank.exceptions.InsufficientFundsException;
import edu.psuti.pp.practice.bank.service.Converter;

public abstract class Account {

    protected static final long DEFAULT_BALANCE = 0;
    protected static final long DEFAULT_COMMISSION = 0;
    protected static final Currency DEFAULT_CURRENCY = Currency.USD;
    private static final int ZERO = 0;

    protected long balance;
    private int id;

    private long commission;
    protected Currency currency;

    public Account(int id) {
        this(id,
                DEFAULT_BALANCE,
                DEFAULT_COMMISSION,
                DEFAULT_CURRENCY
        );
    }

    public Account(int id, double balance) {
        this(id,
                balance,
                DEFAULT_COMMISSION,
                DEFAULT_CURRENCY
        );
    }

    public Account(int id, double balance, double commission) {
        this(id,
                balance,
                commission,
                DEFAULT_CURRENCY
        );
    }

    public Account(int id,
                   double balance,
                   double commission,
                   Currency currentCurrency) {

        this.id = id;
        this.balance = Converter.doubleToLong(balance);
        this.commission = Converter.doubleToLong(commission);
        this.currency = currentCurrency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return Converter.longToDouble(balance);
    }

    public double getCommission() {
        return Converter.longToDouble(commission);
    }

    public void setCommission(double commission) {
        this.commission = Converter.doubleToLong(commission);
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        Currency oldCurrency = this.currency;
        this.currency = currency;
        convertCurrency(oldCurrency, currency);
    }

    public void debitCommissionFromBalance() throws InsufficientFundsException {
        if (commission > getBalance()) {
            throw new InsufficientFundsException();
        }
        balance -= commission;
    }

    public void addToBalance(double value) throws InsufficientFundsException {
        if (value < ZERO || ZERO - value > balance) {
            throw new InsufficientFundsException();
        }
        this.balance += Converter.doubleToLong(value);
    }

    public void debitTheBalance(double value) throws InsufficientFundsException {
        if (value > balance) {
            throw new InsufficientFundsException();
        }
        balance -= Converter.doubleToLong(value);
    }

    private void convertCurrency(Currency oldCurrency, Currency newCurrency) {
        commission = Converter.doubleToLong(
                Converter.recountValue(
                        Converter.longToDouble(commission),
                        oldCurrency,
                        newCurrency
                )
        );
        balance = Converter.doubleToLong(
                Converter.recountValue(
                        Converter.longToDouble(balance),
                        oldCurrency,
                        newCurrency
                )
        );
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("balance=").append(balance);
        sb.append(", id=").append(id);
        sb.append(", commission=").append(commission);
        sb.append(", currency=").append(currency);
        sb.append('}');
        return sb.toString();
    }
}
