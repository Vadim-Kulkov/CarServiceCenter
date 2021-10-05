/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;

import edu.psuti.pp.practice.bank.sevice.Client;

import java.util.ArrayList;

public class NaturalClient implements Client {

    private final ArrayList<Account> bills; // Массив счетов
    private final static ArrayList<Account> default_accounts = new ArrayList<>();
    private final String Name;
    private final String Familia;
    private final int Seria;  // Серия паспорт
    private final int Number; // номер паспорта

    public NaturalClient(String Name, String Familia, int Seria, int Number) {
        this(Name, Familia, Seria, Number, default_accounts);
    }

    public NaturalClient(String Name, String Familia, int Seria, int Number, ArrayList<Account> bills) {
        this.Name = Name;
        this.Familia = Familia;
        this.Seria = Seria;
        this.Number = Number;
        this.bills = new ArrayList<>(bills);
    }

    // метод, возвращающий ссылку на счёт по его уникальному номеру
    @Override
    public Account clientLink(int Number) throws NullPointerException {
        for (Account bill : bills) {
            if (bill.getId() == Number)
                return bill;
        }
        return null;
    }

    // метод, возвращающий массив всех счетов
    @Override
    public ArrayList<Account> getBills() {
        return new ArrayList<>(bills);         // Возвращаем копию, чтобы не было проблем
    }

    // возвращающий список (класс ArrayList<Account>) счетов дебетовых карт
    @Override
    public ArrayList<Account> getDebitList() {
        ArrayList<Account> result = new ArrayList<>();
        for (Account i : bills) {
            if (i instanceof DebitAccount) {
                result.add(i);
            }
        }
        return result;
    }

    // метод, возвращающий список (класс ArrayList<Account>) счетов кредитных карт
    @Override
    public ArrayList<Account> getCreditList() {
        ArrayList<Account> result = new ArrayList<>();
        for (Account i : bills) {
            if (i instanceof CreditAccount) {
                result.add(i);
            }
        }
        return result;
    }

    // метод, возвращающий суммарный остаток на всех дебетовых счетах
    @Override
    public double getDebitBalance() {
        double result = 0.0;
        for (Account i : bills) {
            if (i instanceof DebitAccount) {
                result += i.getBalance();
            }
        }
        return result;
    }

    // метод, возвращающий сумму долга клиента (сумма начисленных процентов
    // и комиссионных по всем кредитным счетам, а также отрицательный остаток по картам)
    @Override
    public double getDuty() {
        double result = 0.0;
        for (Account i : bills) {
            if (i instanceof CreditAccount) {
                result += ((CreditAccount) i).getAssessedPercent() +
                        (((CreditAccount) i).getAssessedCommission());
            }
            if (i.getBalance() < 0.1) {
                result += i.getBalance();
            }
        }
        return result;
    }

    // метод, возвращающий список (класс ArrayList<Account>) счетов с
    //   положительным остатком на счете
    @Override
    public ArrayList<Account> getPositiveSummaryBalance() {
        ArrayList<Account> summaryPositiveBalance = new ArrayList<>();
        for (Account i : bills) {
            if (i.getBalance() > 0.0) {
                summaryPositiveBalance.add(i);
            }
        }
        return summaryPositiveBalance;
    }

    // метод удаления счета по его номеру
    @Override
    public void delBill(int number) {
        for (int i = 0; i < bills.size(); i++) {
            if (bills.get(i).getId() == number) {
                bills.remove(i);
                break;
            }
        }
    }

    // метод добавления счета (принимает в качестве
    // входного параметра ссылку на счет)
    @Override
    public void addBill(Account acc) {
        bills.add(acc);
    }

    // метод списывания средств со счета (принимает номер счета и размер суммы)
    @Override
    public void decreaseBill(int number, double value) {
        for (Account bill : bills) {
            if (bill.getId() == number) {
                bill.balance -= value;
                return;
            }
        }
    }

    // метод пополнения счета (принимает номер счета и размер суммы)
    @Override
    public void increaseBill(int number, double value) {
        for (Account bill : bills) {
            if (bill.getId() == number) {
                bill.balance += value;
                return;
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NaturalClient{");
        sb.append("bills=").append(bills);
        sb.append(", Name='").append(Name).append('\'');
        sb.append(", Familia='").append(Familia).append('\'');
        sb.append(", Seria=").append(Seria);
        sb.append(", Number=").append(Number);
        sb.append('}');
        return sb.toString();
    }

    // метод, возвращающий суммарный остаток на всех счетах
    public double getSummaryBalance() {
        double summaryBalance = 0.0;
        for (Account i : bills) {
            summaryBalance += i.getBalance();
        }
        return summaryBalance;
    }

    // метод уменьшения размера остатка счета
    // (принимает ссылку на счет и размер суммы)
    public void decBalance(Account acc, double sum) {
        acc.balance = doubleToLong(acc.getBalance() - sum);
    }

    //  метод увеличения размера остатка счета (принимает ссылку на
    // счет и размер суммы)
    public void incBalance(Account acc, double sum) {
        acc.balance += sum;
    }

    private long doubleToLong(double balance) {
        return (long) (balance * 100);
    }

    private double longToDouble(double balance) {
        return (balance / 100.0);
    }
}
