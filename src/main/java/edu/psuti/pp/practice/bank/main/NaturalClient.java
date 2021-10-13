/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;

import edu.psuti.pp.practice.bank.service.Client;
import edu.psuti.pp.practice.bank.service.Converter;

import java.util.ArrayList;
import java.util.List;

public class NaturalClient implements Client {
    private static final int ZERO = 0;
    private final List<Account> accountList;
    private final String firstName;
    private final String lastName;
    private final String middleName;
    private final int passportTally;
    private final int passportNumber;

    public NaturalClient(String firstName,
                         String middleName,
                         String lastName,
                         int passportTally,
                         int passportNumber) {
        this(
                firstName,
                middleName,
                lastName,
                passportTally,
                passportNumber,
                new ArrayList<>()
        );
    }

    public NaturalClient(String firstName,
                         String middleName,
                         String lastName,
                         int passportTally,
                         int passportNumber,
                         List<Account> accountList) {

        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.passportTally = passportTally;
        this.passportNumber = passportNumber;
        this.accountList = new ArrayList<>(accountList);
    }

    @Override
    public Account getLinkToAccount(int id) throws NullPointerException {
        for (Account account : accountList) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }

    @Override
    public List<Account> getAccountList() {
        return new ArrayList<>(accountList);
    }

    @Override
    public List<Account> getDebitList() {
        return getListOfType(DebitAccount.class);
    }

    @Override
    public List<Account> getCreditList() {
        return getListOfType(CreditAccount.class);
    }

    private List<Account> getListOfType(Class<?> clazz) {
        List<Account> result = new ArrayList<>();
        for (Account i : accountList) {
            if (clazz.isAssignableFrom(i.getClass())) {
                result.add(i);
            }
        }
        return result;
    }

    @Override
    public double getDebitAccountsBalance() {
        double result = ZERO;
        for (Account i : accountList) {
            if (i instanceof DebitAccount) {
                result += i.getBalance();
            }
        }
        return result;
    }

    @Override
    public double getDebt() {
        double result = ZERO;
        for (Account account : accountList) {
            if (account instanceof CreditAccount) {
                result += ((CreditAccount) account).getAssessedPercent() +
                        (((CreditAccount) account).getAssessedCommission());
            }
            if (account.getBalance() < 0) {
                result += account.getBalance();
            }
        }
        return result;
    }

    @Override
    public List<Account> getAffirmativeSummaryBalance() {
        List<Account> summaryAffirmativeBalance = new ArrayList<>();
        for (Account account : accountList) {
            if (account.getBalance() > ZERO) {
                summaryAffirmativeBalance.add(account);
            }
        }
        return summaryAffirmativeBalance;
    }

    @Override
    public void deleteAccount(int id) {
        accountList.removeIf(i -> i.getId() == id);

    }

    @Override
    public void addAccount(Account account) {
        accountList.add(account);
    }

    @Override
    public void debitBalanceById(int id, double value) {
        for (Account account : accountList) {
            if (account.getId() == id) {
                account.balance -= value;
                return;
            }
        }
    }

    @Override
    public void addToBalanceById(int id, double value) {
        for (Account account : accountList) {
            if (account.getId() == id) {
                account.balance += value;
                return;
            }
        }
    }

    public double getSummaryBalance() {
        double summaryBalance = ZERO;
        for (Account account : accountList) {
            summaryBalance += account.getBalance();
        }
        return summaryBalance;
    }

    public void debitBalanceByAccount(Account account, double value) {
        account.balance = Converter.doubleToLong(account.getBalance() - value);
    }

    public void addToBalanceByAccount(Account account, double value) {
        account.balance += value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NaturalClient{");
        sb.append("accountList=").append(accountList);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", middleName='").append(middleName).append('\'');
        sb.append(", passportTally=").append(passportTally);
        sb.append(", passportNumber=").append(passportNumber);
        sb.append('}');
        return sb.toString();
    }
}
