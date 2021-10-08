/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;

import edu.psuti.pp.practice.bank.exceptions.InsufficientFundsException;
import edu.psuti.pp.practice.bank.service.Recount;

public abstract class Account {

    protected final static long default_balance = 0;
    protected final static long default_commission = 0;
    protected final static Currency default_valut = Currency.USD;

    protected long balance;
    private int id;

    private long commission;
    protected Currency valut;

    public Account(int id) {
        this(id,
                default_balance,
                default_commission,
                default_valut);
    }

    public Account(int id, double balance) {
        this(id,
                balance,
                default_commission,
                default_valut);
    }

    public Account(int id, double balance, double commission) {
        this(id,
                balance,
                commission,
                default_valut);
    }

    public Account(int id,
                   double balance,
                   double commission,
                   Currency valut) {

        this.id = id;
        this.balance = Recount.doubleToLong(balance);
        this.commission = Recount.doubleToLong(commission);
        this.valut = valut;
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

    public Currency getValut() {
        return valut;
    }

    public void setValut(Currency NewCurrency) {
        changeCurrency(NewCurrency);
        valut = NewCurrency;
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
    public void popBalance(double value) throws InsufficientFundsException {
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
        sb.append(", valut=").append(valut);
        sb.append('}');
        return sb.toString();
    }

    private void changeCurrency(Currency newCurrency) {
        commission = Recount.doubleToLong(
                Recount.recountValue(
                        Recount.longToDouble(commission),
                        valut,
                        newCurrency));
        balance = Recount.doubleToLong(
                Recount.recountValue(
                        Recount.longToDouble(balance),
                        valut,
                        newCurrency));
    }
}
