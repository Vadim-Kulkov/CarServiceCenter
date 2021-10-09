/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;

import edu.psuti.pp.practice.bank.exceptions.InsufficientFundsException;
import edu.psuti.pp.practice.bank.service.Recount;

public abstract class Account {

    protected final static long DEFAULT_BALANCE = 0;
    protected final static long DEFAULT_COMMISSION = 0;
    protected final static Currency DEFAULT_CURRENT_CURRENCY = Currency.USD;

    protected long balance;
    private int id;

    private long commission;
    protected Currency currentCurrency;

    public Account(int id) {
        this(id,
                DEFAULT_BALANCE,
                DEFAULT_COMMISSION,
                DEFAULT_CURRENT_CURRENCY);
    }

    public Account(int id, double balance) {
        this(id,
                balance,
                DEFAULT_COMMISSION,
                DEFAULT_CURRENT_CURRENCY);
    }

    public Account(int id, double balance, double commission) {
        this(id,
                balance,
                commission,
                DEFAULT_CURRENT_CURRENCY);
    }

    public Account(int id,
                   double balance,
                   double commission,
                   Currency currentCurrency) {

        this.id = id;
        this.balance = Recount.doubleToLong(balance);
        this.commission = Recount.doubleToLong(commission);
        this.currentCurrency = currentCurrency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return Recount.longToDouble(balance);
    }

    public double getCommission() {
        return Recount.longToDouble(commission);
    }

    public void setCommission(double commission) {
        this.commission = Recount.doubleToLong(commission);
    }

    public Currency getCurrentCurrency() {
        return currentCurrency;
    }

    public void setCurrentCurrency(Currency NewCurrency) {
        changeCurrency(NewCurrency);
        currentCurrency = NewCurrency;
    }

    // метод, вычитающий комиссию из остатка
    public void commissionFromBalance() throws InsufficientFundsException {
        if (commission > getBalance()) {
            throw new InsufficientFundsException();
        }
        balance -= commission;
    }

    //  метод пополнения счета
    public void addToBalance(double value) throws InsufficientFundsException {
        if (value < 0 || 0.1 - value > balance) {
            throw new InsufficientFundsException();
        }
        this.balance += Recount.doubleToLong(value);
    }

    // метод списывания суммы со счёта
    public void deductFromTheBalance(double value) throws InsufficientFundsException {
        if (value > balance) {
            throw new InsufficientFundsException();
        }
        balance -= Recount.doubleToLong(value);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("balance=").append(balance);
        sb.append(", id=").append(id);
        sb.append(", сommission=").append(commission);
        sb.append(", currentСurrency=").append(currentCurrency);
        sb.append('}');
        return sb.toString();
    }

    private void changeCurrency(Currency newCurrency) {
        commission = Recount.doubleToLong(
                Recount.recountValue(
                        Recount.longToDouble(commission),
                        currentCurrency,
                        newCurrency
                )
        );
        balance = Recount.doubleToLong(
                Recount.recountValue(
                        Recount.longToDouble(balance),
                        currentCurrency,
                        newCurrency
                )
        );
    }
}
