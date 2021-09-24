/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;

import edu.psuti.pp.practice.bank.sevice.Client;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class NaturalClient implements Client {

    private final ArrayList<Account> bills; // Массив счетов

    private final String Name;
    private final String Familia;
    private final int Seria;  // Серия паспорт
    private final int Number; // номер паспорта

    public NaturalClient(String Name, String Familia, int Seria, int Number) {
        this.Name = Name;
        this.Familia = Familia;
        this.Seria = Seria;
        this.Number = Number;
        bills = new ArrayList<>(0);
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
            if (bill.getNumber() == Number)
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
    public double getDebitOst() {
        double result = 0.0;
        for (Account i : bills) {
            if (i instanceof DebitAccount) {
                result += i.getOst();
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
                        (((CreditAccount) i).getAssessedComission());
            }
            if (i.getOst() < 0.1) {
                result += i.getOst();
            }
        }
        return result;
    }

    // метод, возвращающий список (класс ArrayList<Account>) счетов с
    //   положительным остатком на счете
    @Override
    public ArrayList<Account> getPositiveSummaryOst() {
        ArrayList<Account> summaryPositiveOst = new ArrayList<>();
        for (Account i : bills) {
            if (i.getOst() > 0.0) {
                summaryPositiveOst.add(i);
            }
        }
        return summaryPositiveOst;
    }

    // метод удаления счета по его номеру
    @Override
    public void delBill(int number) {
        for (int i = 0; i < bills.size(); i++) {
            if (bills.get(i).getNumber() == number) {
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
            if (bill.getNumber() == number) {
                bill.ost -= value;
                return;
            }
        }
    }

    // метод пополнения счета (принимает номер счета и размер суммы)
    @Override
    public void increaseBill(int number, double value) {
        for (Account bill : bills) {
            if (bill.getNumber() == number) {
                bill.ost += value;
                return;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        Class account = Account.class;
        Method[] methods = account.getDeclaredMethods();

        answer.append(getClass()).append('\n');

        answer.append("private final String Name: ")
                .append(Name)
                .append('\n');

        answer.append("private final String Familia: ")
                .append(Familia)
                .append('\n');

        answer.append("private final int Seria: ")
                .append(Seria)
                .append('\n');

        answer.append("private final int Number: ")
                .append(Number)
                .append('\n');

        for (Method i : methods) {
            answer.append(i)
                    .append('\n');
        }
        return answer.toString();
    }

    // метод, возвращающий суммарный остаток на всех счетах
    public double getSummaryOst() {
        double summaryOst = 0.0;
        for (Account i : bills) {
            summaryOst += i.getOst();
        }
        return summaryOst;
    }

    // метод уменьшения размера остатка счета
    // (принимает ссылку на счет и размер суммы)
    public void decOst(Account acc, double sum) {
        acc.ost = doubleToLong(acc.getOst() - sum);
    }

    //  метод увеличения размера остатка счета (принимает ссылку на
    // счет и размер суммы)
    public void incOst(Account acc, double sum) {
        acc.ost += sum;
    }

    private long doubleToLong(double ost) {
        return (long) (ost * 100);
    }

    private double longToDouble(double ost) {
        return (ost / 100.0);
    }
}
