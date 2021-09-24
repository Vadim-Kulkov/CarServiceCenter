package edu.psuti.pp.practice.bank;

import java.util.ArrayList;

public interface Client {
    // метод, возвращающий ссылку на счёт по его уникальному номеру
    public Account clientLink(int Number);

    // возвращающий список (класс ArrayList<Account>) всех счетов
    public ArrayList<Account> getBills();

    // возвращающий список (класс ArrayList<Account>) счетов дебетовых карт
    public ArrayList<Account> getDebitList();

    // метод, возвращающий список (класс ArrayList<Account>) счетов кредитных карт
    public ArrayList<Account> getCreditList();

    // метод, возвращающий суммарный остаток на всех дебетовых счетах
    public double getDebitOst();

    // метод, возвращающий сумму долга клиента (сумма начисленных процентов
    // и комиссионных по всем кредитным счетам, а также отрицательный остаток по картам)
    public double getDuty();

    // метод, возвращающий список (класс ArrayList<Account>) счетов с
    // положительным остатком на счете
    public ArrayList<Account> getPositiveSummaryOst();

    // метод удаления счета по его номеру
    public void delBill(int number);

    // метод добавления счета (принимает в качестве входного параметра ссылку на счет)
    public void addBill(Account acc);

    // метод списывания средств со счета (принимает номер счета и размер суммы)
    public void decreaseBill(int number, double value);

    // метод пополнения счета (принимает номер счета и размер суммы)
    public void increaseBill(int number, double value);
}
