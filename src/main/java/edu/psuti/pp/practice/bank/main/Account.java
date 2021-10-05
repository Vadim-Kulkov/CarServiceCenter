/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;

import edu.psuti.pp.practice.bank.exceptions.InsufficientFundsException;

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
        this.balance = doubleToLong(balance);
        this.commission = doubleToLong(commission);
        this.valut = valut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return longToDouble(balance);
    }

    public double getCommission() {
        return longToDouble(commission);
    }

    public void setCommission(double commission) {
        this.commission = doubleToLong(commission);
    }

    public Currency getValut() {
        return valut;
    }

    public void setValut(Currency valut) {
        pereschet(valut);
        this.valut = valut;
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
        this.balance += doubleToLong(value);
    }

    // метод списывания суммы со счёта
    public void popBalance(double value) throws InsufficientFundsException {
        if (value > balance) {
            throw new InsufficientFundsException();
        }
        balance += doubleToLong(value);
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

    protected long doubleToLong(double balance) {
        return (long) (balance * 100);
    }

    protected double longToDouble(double balance) {
        return (balance / 100.0);
    }

    public void pereschet(Currency new_valut) {
        if (this.valut == Currency.USD) {
            if (new_valut == Currency.EUR) {
                this.setCommission(this.getCommission() * 0.85);
                this.balance = doubleToLong(this.getBalance() * 0.85);
            } else if (new_valut == Currency.JOY) {
                this.setCommission(this.getCommission() * 109.6);
                this.balance = doubleToLong(this.getBalance() * 109.6);
            } else if (new_valut == Currency.TRY) {
                this.setCommission(this.getCommission() * 8.64);
                this.balance = doubleToLong(this.getBalance() * 8.64);
            } else if (new_valut == Currency.AED) {
                this.setCommission(this.getCommission() * 3.67);
                this.balance = doubleToLong(this.getBalance() * 3.67);
            } else if (new_valut == Currency.RUB) {
                this.setCommission(this.getCommission() * 72.8);
                this.balance = doubleToLong(this.getBalance() * 72.80);
            }
        } else if (this.valut == Currency.EUR) {
            if (new_valut == Currency.USD) {
                this.setCommission(this.getCommission() * 1.17);
                this.balance = doubleToLong(this.getBalance() * 1.17);
            } else if (new_valut == Currency.JOY) {
                this.setCommission(this.getCommission() * 0.6);
                this.balance = doubleToLong(this.getBalance() * 0.6);
            } else if (new_valut == Currency.TRY) {
                this.setCommission(this.getCommission() * 10.14);
                this.balance = doubleToLong(this.getBalance() * 10.14);
            } else if (new_valut == Currency.AED) {
                this.setCommission(this.getCommission() * 4.31);
                this.balance = doubleToLong(this.getBalance() * 4.31);
            } else if (new_valut == Currency.RUB) {
                this.setCommission(this.getCommission() * 85.5);
                this.balance = doubleToLong(this.getBalance() * 85.5);
            }
        } else if (this.valut == Currency.JOY) {
            if (new_valut == Currency.EUR) {
                this.setCommission(this.getCommission() * 0.0078);
                this.balance = doubleToLong(this.getBalance() * 0.0078);
            } else if (new_valut == Currency.USD) {
                this.setCommission(this.getCommission() * 0.0091);
                this.balance = doubleToLong(this.getBalance() * 0.0091);
            } else if (new_valut == Currency.TRY) {
                this.setCommission(this.getCommission() * 0.079);
                this.balance = doubleToLong(this.getBalance() * 0.079);
            } else if (new_valut == Currency.AED) {
                this.setCommission(this.getCommission() * 0.034);
                this.balance = doubleToLong(this.getBalance() * 0.034);
            } else if (new_valut == Currency.RUB) {
                this.setCommission(this.getCommission() * 0.66);
                this.balance = doubleToLong(this.getBalance() * 0.66);
            }
        } else if (this.valut == Currency.TRY) {
            if (new_valut == Currency.EUR) {
                this.setCommission(this.getCommission() * 0.099);
                this.balance = doubleToLong(this.getBalance() * 0.099);
            } else if (new_valut == Currency.JOY) {
                this.setCommission(this.getCommission() * 12.69);
                this.balance = doubleToLong(this.getBalance() * 12.69);
            } else if (new_valut == Currency.USD) {
                this.setCommission(this.getCommission() * 0.12);
                this.balance = doubleToLong(this.getBalance() * 0.12);
            } else if (new_valut == Currency.AED) {
                this.setCommission(this.getCommission() * 0.43);
                this.balance = doubleToLong(this.getBalance() * 0.43);
            } else if (new_valut == Currency.RUB) {
                this.setCommission(this.getCommission() * 8.43);
                this.balance = doubleToLong(this.getBalance() * 8.43);
            }
        } else if (this.valut == Currency.AED) {
            if (new_valut == Currency.EUR) {
                this.setCommission(this.getCommission() * 0.23);
                this.balance = doubleToLong(this.getBalance() * 0.23);
            } else if (new_valut == Currency.JOY) {
                this.setCommission(this.getCommission() * 29.84);
                this.balance = doubleToLong(this.getBalance() * 29.84);
            } else if (new_valut == Currency.USD) {
                this.setCommission(this.getCommission() * 0.27);
                this.balance = doubleToLong(this.getBalance() * 0.27);
            } else if (new_valut == Currency.TRY) {
                this.setCommission(this.getCommission() * 2.35);
                this.balance = doubleToLong(this.getBalance() * 2.35);
            } else if (new_valut == Currency.RUB) {
                this.setCommission(this.getCommission() * 19.84);
                this.balance = doubleToLong(this.getBalance() * 19.84);
            }
        } else if (this.valut == Currency.RUB) {
            if (new_valut == Currency.EUR) {
                this.setCommission(this.getCommission() * 0.012);
                this.balance = doubleToLong(this.getBalance() * 0.012);
            } else if (new_valut == Currency.JOY) {
                this.setCommission(this.getCommission() * 1.50);
                this.balance = doubleToLong(this.getBalance() * 1.50);
            } else if (new_valut == Currency.USD) {
                this.setCommission(this.getCommission() * 0.014);
                this.balance = doubleToLong(this.getBalance() * 0.014);
            } else if (new_valut == Currency.TRY) {
                this.setCommission(this.getCommission() * 0.12);
                this.balance = doubleToLong(this.getBalance() * 0.12);
            } else if (new_valut == Currency.AED) {
                this.setCommission(this.getCommission() * 0.050);
                this.balance = doubleToLong(this.getBalance() * 0.050);
            }
        }
    }
}
