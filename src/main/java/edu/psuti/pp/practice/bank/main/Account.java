/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;

import edu.psuti.pp.practice.bank.exceptions.InsufficientFundsException;

import java.lang.reflect.Method;

public abstract class Account {

    protected long ost;       // остаток в копейках, чтобы не реализовывать double
    private int number;     // уникальный номер

    private long comiss;
    protected Currency valut = Currency.USD;

    public Account(int number) {
        this.number = number;
        ost = 0;
    }

    public Account(int number, double ost) {
        this.number = number;
        this.ost = doubleToLong(ost);
    }

    public Account(int number, double ost, double comiss) {
        this.number = number;
        this.ost = doubleToLong(ost);
        this.comiss = doubleToLong(comiss);
    }

    public Account(int number,
                   double ost,
                   double comiss,
                   Currency valut) {

        this.number = number;
        this.ost = doubleToLong(ost);
        this.comiss = doubleToLong(comiss);
        this.valut = valut;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getOst() {
        return longToDouble(ost);
    }

    public double getComiss() {
        return longToDouble(comiss);
    }

    public void setComiss(double comiss) {
        this.comiss = doubleToLong(comiss);
    }

    public Currency getValut() {
        return valut;
    }

    public void setValut(Currency valut) {
        pereschet(valut);
        this.valut = valut;
    }

    // метод, вычитающий комиссию из остатка
    public void comissFromOst() {
        try {
            if (comiss > getOst()) {
                throw new InsufficientFundsException();
            }
        } catch (InsufficientFundsException e) {
            e.getMessage();
        }
        this.ost -= this.comiss;
    }

    //  метод пополнения счета
    public void spisFromOst(double value) {
        try {
            if (value < 0 || 0.1 - value > ost) {
                throw new InsufficientFundsException();
            }
        } catch (InsufficientFundsException e) {
            e.getMessage();
        }
        this.ost += doubleToLong(value);
    }

    // метод списывания суммы со счета
    public void popOst(double value) {
        try {
            if (value > ost) {
                throw new InsufficientFundsException();
            }
        } catch (InsufficientFundsException e) {
            e.getMessage();
        }
        ost += doubleToLong(value);
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        Class account = Account.class;
        Method[] methods = account.getDeclaredMethods();

        answer.append(getClass()).append('\n')
                .append("number ").append(getNumber())
                .append('\n').append("ost ")
                .append(getOst()).append('\n');

        answer.append("comiss ")
                .append(getComiss())
                .append('\n');

        answer.append("valut ")
                .append(getValut())
                .append('\n');

        for (Method i : methods) {
            answer.append(i).append('\n');
        }
        return answer.toString();
    }

    protected long doubleToLong(double ost) {
        return (long) (ost * 100);
    }

    protected double longToDouble(double ost) {
        return (ost / 100.0);
    }

    public void pereschet(Currency new_valut) {
        if (this.valut == Currency.USD) {
            if (new_valut == Currency.EUR) {
                this.setComiss(this.getComiss() * 0.85);
                this.ost = doubleToLong(this.getOst() * 0.85);
            } else if (new_valut == Currency.JOY) {
                this.setComiss(this.getComiss() * 109.6);
                this.ost = doubleToLong(this.getOst() * 109.6);
            } else if (new_valut == Currency.TRY) {
                this.setComiss(this.getComiss() * 8.64);
                this.ost = doubleToLong(this.getOst() * 8.64);
            } else if (new_valut == Currency.AED) {
                this.setComiss(this.getComiss() * 3.67);
                this.ost = doubleToLong(this.getOst() * 3.67);
            } else if (new_valut == Currency.RUB) {
                this.setComiss(this.getComiss() * 72.8);
                this.ost = doubleToLong(this.getOst() * 72.80);
            }
        } else if (this.valut == Currency.EUR) {
            if (new_valut == Currency.USD) {
                this.setComiss(this.getComiss() * 1.17);
                this.ost = doubleToLong(this.getOst() * 1.17);
            } else if (new_valut == Currency.JOY) {
                this.setComiss(this.getComiss() * 0.6);
                this.ost = doubleToLong(this.getOst() * 0.6);
            } else if (new_valut == Currency.TRY) {
                this.setComiss(this.getComiss() * 10.14);
                this.ost = doubleToLong(this.getOst() * 10.14);
            } else if (new_valut == Currency.AED) {
                this.setComiss(this.getComiss() * 4.31);
                this.ost = doubleToLong(this.getOst() * 4.31);
            } else if (new_valut == Currency.RUB) {
                this.setComiss(this.getComiss() * 85.5);
                this.ost = doubleToLong(this.getOst() * 85.5);
            }
        } else if (this.valut == Currency.JOY) {
            if (new_valut == Currency.EUR) {
                this.setComiss(this.getComiss() * 0.0078);
                this.ost = doubleToLong(this.getOst() * 0.0078);
            } else if (new_valut == Currency.USD) {
                this.setComiss(this.getComiss() * 0.0091);
                this.ost = doubleToLong(this.getOst() * 0.0091);
            } else if (new_valut == Currency.TRY) {
                this.setComiss(this.getComiss() * 0.079);
                this.ost = doubleToLong(this.getOst() * 0.079);
            } else if (new_valut == Currency.AED) {
                this.setComiss(this.getComiss() * 0.034);
                this.ost = doubleToLong(this.getOst() * 0.034);
            } else if (new_valut == Currency.RUB) {
                this.setComiss(this.getComiss() * 0.66);
                this.ost = doubleToLong(this.getOst() * 0.66);
            }
        } else if (this.valut == Currency.TRY) {
            if (new_valut == Currency.EUR) {
                this.setComiss(this.getComiss() * 0.099);
                this.ost = doubleToLong(this.getOst() * 0.099);
            } else if (new_valut == Currency.JOY) {
                this.setComiss(this.getComiss() * 12.69);
                this.ost = doubleToLong(this.getOst() * 12.69);
            } else if (new_valut == Currency.USD) {
                this.setComiss(this.getComiss() * 0.12);
                this.ost = doubleToLong(this.getOst() * 0.12);
            } else if (new_valut == Currency.AED) {
                this.setComiss(this.getComiss() * 0.43);
                this.ost = doubleToLong(this.getOst() * 0.43);
            } else if (new_valut == Currency.RUB) {
                this.setComiss(this.getComiss() * 8.43);
                this.ost = doubleToLong(this.getOst() * 8.43);
            }
        } else if (this.valut == Currency.AED) {
            if (new_valut == Currency.EUR) {
                this.setComiss(this.getComiss() * 0.23);
                this.ost = doubleToLong(this.getOst() * 0.23);
            } else if (new_valut == Currency.JOY) {
                this.setComiss(this.getComiss() * 29.84);
                this.ost = doubleToLong(this.getOst() * 29.84);
            } else if (new_valut == Currency.USD) {
                this.setComiss(this.getComiss() * 0.27);
                this.ost = doubleToLong(this.getOst() * 0.27);
            } else if (new_valut == Currency.TRY) {
                this.setComiss(this.getComiss() * 2.35);
                this.ost = doubleToLong(this.getOst() * 2.35);
            } else if (new_valut == Currency.RUB) {
                this.setComiss(this.getComiss() * 19.84);
                this.ost = doubleToLong(this.getOst() * 19.84);
            }
        } else if (this.valut == Currency.RUB) {
            if (new_valut == Currency.EUR) {
                this.setComiss(this.getComiss() * 0.012);
                this.ost = doubleToLong(this.getOst() * 0.012);
            } else if (new_valut == Currency.JOY) {
                this.setComiss(this.getComiss() * 1.50);
                this.ost = doubleToLong(this.getOst() * 1.50);
            } else if (new_valut == Currency.USD) {
                this.setComiss(this.getComiss() * 0.014);
                this.ost = doubleToLong(this.getOst() * 0.014);
            } else if (new_valut == Currency.TRY) {
                this.setComiss(this.getComiss() * 0.12);
                this.ost = doubleToLong(this.getOst() * 0.12);
            } else if (new_valut == Currency.AED) {
                this.setComiss(this.getComiss() * 0.050);
                this.ost = doubleToLong(this.getOst() * 0.050);
            }
        }
    }
}
