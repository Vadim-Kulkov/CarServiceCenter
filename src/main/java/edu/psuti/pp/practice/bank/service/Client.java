/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.service;

import edu.psuti.pp.practice.bank.main.Account;

import java.util.List;

public interface Client {

    Account getLinkToAccount(int id);

    List<Account> getAccountList();

    List<Account> getDebitList();

    List<Account> getCreditList();

    double getDebitAccountsBalance();

    double getDebt();

    List<Account> getAffirmativeSummaryBalance();

    void deleteAccount(int id);

    void addAccount(Account account);

    void debitBalanceById(int id, double value);

    void addToBalanceById(int id, double value);
}
