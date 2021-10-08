/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.service;

import edu.psuti.pp.practice.bank.main.Account;

import java.util.ArrayList;

public interface Client {

    // метод, возвращающий ссылку на счёт по его уникальному номеру
    Account clientLink(int Number);

    // возвращающий список (класс ArrayList<Account>) всех счетов
    ArrayList<Account> getBills();

    // возвращающий список (класс ArrayList<Account>) счетов дебетовых карт
    ArrayList<Account> getDebitList();

    // метод, возвращающий список (класс ArrayList<Account>) счетов кредитных карт
    ArrayList<Account> getCreditList();

    // метод, возвращающий суммарный остаток на всех дебетовых счетах
    double getDebitBalance();

    // метод, возвращающий сумму долга клиента (сумма начисленных процентов
    // и комиссионных по всем кредитным счетам, а также отрицательный остаток по картам)
    double getDuty();

    // метод, возвращающий список (класс ArrayList<Account>) счетов с
    // положительным остатком на счете
    ArrayList<Account> getPositiveSummaryBalance();

    // метод удаления счета по его номеру
    void delBill(int number);

    // метод добавления счета (принимает в качестве входного параметра ссылку на счет)
    void addBill(Account acc);

    // метод списывания средств со счета (принимает номер счета и размер суммы)
    void decreaseBill(int number, double value);

    // метод пополнения счета (принимает номер счета и размер суммы)
    void increaseBill(int number, double value);
}
