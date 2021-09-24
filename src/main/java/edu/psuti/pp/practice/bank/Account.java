package edu.psuti.pp.practice.bank;

import java.lang.reflect.Method;

import static edu.psuti.pp.practice.bank.AccountNumberGenerator.Currency.*;

public abstract class Account {
    protected long ost;       // остаток в копейках, чтобы не реализовывать double
    private int number;     // уникальный номер

    private long comiss;
    protected AccountNumberGenerator.Currency valut = USD;

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
                   AccountNumberGenerator.Currency valut) {

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

    public AccountNumberGenerator.Currency getValut() {
        return valut;
    }

    public void setValut(AccountNumberGenerator.Currency valut) {
        pereschet(valut);
        this.valut = valut;
    }

    // метод, вычитающий комиссию из остатка
    public void comissFromOst() throws InsufficientFundsException {
        try {
            if (comiss > getOst())
                throw new InsufficientFundsException();
        } catch (InsufficientFundsException e) {
            e.getMessage();
        }
        this.ost -= this.comiss;
    }

    //  метод пополнения счета
    public void spisFromOst(double value) throws InsufficientFundsException {
        try {
            if (value < 0 || 0.1 - value > ost)
                throw new InsufficientFundsException();
        } catch (InsufficientFundsException e) {
            e.getMessage();
        }
        this.ost += doubleToLong(value);
    }

    // метод списывания суммы со счета
    public void popOst(double value) throws InsufficientFundsException {
        try {
            if (value > ost)
                throw new InsufficientFundsException();
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

        answer.append(getClass());
        answer.append('\n');

        answer.append("number ");
        answer.append(getNumber());
        answer.append('\n');

        answer.append("ost ");
        answer.append(getOst());
        answer.append('\n');

        answer.append("comiss ");
        answer.append(getComiss());
        answer.append('\n');

        answer.append("valut ");
        answer.append(getValut());
        answer.append('\n');

        for (Method i : methods) {
            answer.append(i);
            answer.append('\n');
        }
        return answer.toString();
    }

    protected long doubleToLong(double ost) {
        return (long) (ost * 100);
    }

    protected double longToDouble(double ost) {
        return (double) (ost / 100.0);
    }

    public void pereschet(AccountNumberGenerator.Currency new_valut) {
        switch (this.valut) {
            case USD:
                switch (new_valut) {
                    case EUR:
                        this.setComiss(this.getComiss() * 0.85);
                        this.ost = doubleToLong(this.getOst() * 0.85);
                        break;
                    case JOY:
                        this.setComiss(this.getComiss() * 109.6);
                        this.ost = doubleToLong(this.getOst() * 109.6);
                        break;
                    case TRY:
                        this.setComiss(this.getComiss() * 8.64);
                        this.ost = doubleToLong(this.getOst() * 8.64);
                        break;
                    case AED:
                        this.setComiss(this.getComiss() * 3.67);
                        this.ost = doubleToLong(this.getOst() * 3.67);
                        break;
                    case RUB:
                        this.setComiss(this.getComiss() * 72.8);
                        this.ost = doubleToLong(this.getOst() * 72.80);
                        break;
                    default:
                        break;
                }
                break;
            case EUR:
                switch (new_valut) {
                    case USD:
                        this.setComiss(this.getComiss() * 1.17);
                        this.ost = doubleToLong(this.getOst() * 1.17);
                        break;
                    case JOY:
                        this.setComiss(this.getComiss() * 0.6);
                        this.ost = doubleToLong(this.getOst() * 0.6);
                        break;
                    case TRY:
                        this.setComiss(this.getComiss() * 10.14);
                        this.ost = doubleToLong(this.getOst() * 10.14);
                        break;
                    case AED:
                        this.setComiss(this.getComiss() * 4.31);
                        this.ost = doubleToLong(this.getOst() * 4.31);
                        break;
                    case RUB:
                        this.setComiss(this.getComiss() * 85.5);
                        this.ost = doubleToLong(this.getOst() * 85.5);
                        break;
                    default:
                        break;
                }
                break;
            case JOY:
                switch (new_valut) {
                    case EUR:
                        this.setComiss(this.getComiss() * 0.0078);
                        this.ost = doubleToLong(this.getOst() * 0.0078);
                        break;
                    case USD:
                        this.setComiss(this.getComiss() * 0.0091);
                        this.ost = doubleToLong(this.getOst() * 0.0091);
                        break;
                    case TRY:
                        this.setComiss(this.getComiss() * 0.079);
                        this.ost = doubleToLong(this.getOst() * 0.079);
                        break;
                    case AED:
                        this.setComiss(this.getComiss() * 0.034);
                        this.ost = doubleToLong(this.getOst() * 0.034);
                        break;
                    case RUB:
                        this.setComiss(this.getComiss() * 0.66);
                        this.ost = doubleToLong(this.getOst() * 0.66);
                        break;
                    default:
                        break;
                }
                break;
            case TRY:
                switch (new_valut) {
                    case EUR:
                        this.setComiss(this.getComiss() * 0.099);
                        this.ost = doubleToLong(this.getOst() * 0.099);
                        break;
                    case JOY:
                        this.setComiss(this.getComiss() * 12.69);
                        this.ost = doubleToLong(this.getOst() * 12.69);
                        break;
                    case USD:
                        this.setComiss(this.getComiss() * 0.12);
                        this.ost = doubleToLong(this.getOst() * 0.12);
                        break;
                    case AED:
                        this.setComiss(this.getComiss() * 0 / 43);
                        this.ost = doubleToLong(this.getOst() * 0 / 43);
                        break;
                    case RUB:
                        this.setComiss(this.getComiss() * 8.43);
                        this.ost = doubleToLong(this.getOst() * 8.43);
                        break;
                    default:
                        break;
                }
                break;
            case AED:
                switch (new_valut) {
                    case EUR:
                        this.setComiss(this.getComiss() * 0.23);
                        this.ost = doubleToLong(this.getOst() * 0.23);
                        break;
                    case JOY:
                        this.setComiss(this.getComiss() * 29.84);
                        this.ost = doubleToLong(this.getOst() * 29.84);
                        break;
                    case TRY:
                        this.setComiss(this.getComiss() * 2.35);
                        this.ost = doubleToLong(this.getOst() * 2.35);
                        break;
                    case USD:
                        this.setComiss(this.getComiss() * 0.27);
                        this.ost = doubleToLong(this.getOst() * 0.27);
                        break;
                    case RUB:
                        this.setComiss(this.getComiss() * 19.84);
                        this.ost = doubleToLong(this.getOst() * 19.84);
                        break;
                    default:
                        break;
                }
                break;
            case RUB:
                switch (new_valut) {
                    case EUR:
                        this.setComiss(this.getComiss() * 0.012);
                        this.ost = doubleToLong(this.getOst() * 0.012);
                        break;
                    case JOY:
                        this.setComiss(this.getComiss() * 1.50);
                        this.ost = doubleToLong(this.getOst() * 1.50);
                        break;
                    case TRY:
                        this.setComiss(this.getComiss() * 0.12);
                        this.ost = doubleToLong(this.getOst() * 0.12);
                        break;
                    case AED:
                        this.setComiss(this.getComiss() * 0.050);
                        this.ost = doubleToLong(this.getOst() * 0.050);
                        break;
                    case USD:
                        this.setComiss(this.getComiss() * 0.014);
                        this.ost = doubleToLong(this.getOst() * 0.014);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

    }
}
