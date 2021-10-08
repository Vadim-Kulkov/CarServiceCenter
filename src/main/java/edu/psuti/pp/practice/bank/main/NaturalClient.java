/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;

import edu.psuti.pp.practice.bank.service.Client;
import edu.psuti.pp.practice.bank.service.Recount;

import java.util.ArrayList;
import java.util.List;

public class NaturalClient implements Client {

    private final List<Account> accountList;
    private final static List<Account> default_accounts = new ArrayList<>();
    private final String Name;
    private final String Surname;
    private final int Seria;  // Серия паспорт
    private final int PassportNumber;

    public NaturalClient(String Name, String surname, int Seria, int passportNumber) {
        this(Name, surname, Seria, passportNumber, default_accounts);
    }

    public NaturalClient(String Name, String surname, int Seria, int passportNumber, List<Account> accountList) {
        this.Name = Name;
        this.Surname = surname;
        this.Seria = Seria;
        this.PassportNumber = passportNumber;
        this.accountList = new ArrayList<>(accountList);
    }

    // метод, возвращающий ссылку на счёт по его уникальному номеру
    @Override
    public Account getLinkToAccount(int id) throws NullPointerException {
        for (Account account : accountList) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }

    // метод, возвращающий массив всех счетов
    @Override
    public List<Account> getAccountList() {
        return new ArrayList<>(accountList);
    }

    // возвращающий список (класс ArrayList<Account>) счетов дебетовых карт
    @Override
    public List<Account> getDebitList() {
        List<Account> result = new ArrayList<>();
        for (Account i : accountList) {
            if (i instanceof DebitAccount) {
                result.add(i);
            }
        }
        return result;
    }

    // метод, возвращающий список (класс ArrayList<Account>) счетов кредитных карт
    @Override
    public List<Account> getCreditList() {
        List<Account> result = new ArrayList<>();
        for (Account i : accountList) {
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
        for (Account i : accountList) {
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
        for (Account account : accountList) {
            if (account instanceof CreditAccount) {
                result += ((CreditAccount) account).getAssessedPercent() +
                        (((CreditAccount) account).getAssessedCommission());
            }
            if (account.getBalance() < 0.1) {
                result += account.getBalance();
            }
        }
        return result;
    }

    // метод, возвращающий список (класс ArrayList<Account>) счетов с
    //   положительным остатком на счете
    @Override
    public List<Account> getPositiveSummaryBalance() {
        List<Account> summaryPositiveBalance = new ArrayList<>();
        for (Account account : accountList) {
            if (account.getBalance() > 0.0) {
                summaryPositiveBalance.add(account);
            }
        }
        return summaryPositiveBalance;
    }

    // метод удаления счета по его номеру
    @Override
    public void deleteAccount(int id) {
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getId() == id) {
                accountList.remove(i);
                break;
            }
        }
    }

    // метод добавления счета (принимает в качестве
    // входного параметра ссылку на счет)
    @Override
    public void addAccount(Account account) {
        accountList.add(account);
    }

    // метод списывания средств со счета (принимает номер счета и размер суммы)
    @Override
    public void decreaseAccount(int id, double value) {
        for (Account account : accountList) {
            if (account.getId() == id) {
                account.balance -= value;
                return;
            }
        }
    }

    // метод пополнения счета (принимает номер счета и размер суммы)
    @Override
    public void increaseAccount(int id, double value) {
        for (Account account : accountList) {
            if (account.getId() == id) {
                account.balance += value;
                return;
            }
        }
    }

    // метод, возвращающий суммарный остаток на всех счетах
    public double getSummaryBalance() {
        double summaryBalance = 0.0;
        for (Account account : accountList) {
            summaryBalance += account.getBalance();
        }
        return summaryBalance;
    }

    // метод уменьшения размера остатка счета
    // (принимает ссылку на счет и размер суммы)
    public void decBalance(Account account, double sum) {
        account.balance = Recount.doubleToLong(account.getBalance() - sum);
    }

    //  метод увеличения размера остатка счета (принимает ссылку на
    // счет и размер суммы)
    public void incBalance(Account account, double sum) {
        account.balance += sum;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NaturalClient{");
        sb.append("accountList=").append(accountList);
        sb.append(", Name='").append(Name).append('\'');
        sb.append(", Surname='").append(Surname).append('\'');
        sb.append(", Seria=").append(Seria);
        sb.append(", PassportNumber=").append(PassportNumber);
        sb.append('}');
        return sb.toString();
    }
}
