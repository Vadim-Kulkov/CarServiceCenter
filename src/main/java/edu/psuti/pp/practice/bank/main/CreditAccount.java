/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;

import edu.psuti.pp.practice.bank.exceptions.InsufficientFundsException;
import edu.psuti.pp.practice.bank.service.Recount;

import java.util.Calendar;
import java.util.Date;

public class CreditAccount extends Account {

    // процентная ставка
    private double percentRate;
    // лимит по кредитной карте
    private double creditCardLimit;
    // начисленные проценты
    private double assessedPercent;
    // начисленные комиссионные
    private double assessedCommission;

    private static final double DEFAULT_PERCENT_RATE = 10.0;
    private static final double DEFAULT_CREDIT_CARD_LIMIT = 100_000;

    public CreditAccount(int id) {
        this(id, DEFAULT_BALANCE, DEFAULT_COMMISSION, DEFAULT_CURRENT_CURRENCY, DEFAULT_PERCENT_RATE, DEFAULT_CREDIT_CARD_LIMIT);
    }

    public CreditAccount(int id, double balance) {
        this(id, balance, DEFAULT_COMMISSION, DEFAULT_CURRENT_CURRENCY, DEFAULT_PERCENT_RATE, DEFAULT_CREDIT_CARD_LIMIT);
    }

    public CreditAccount(int id, double balance, double commission) {
        this(id, balance, commission, DEFAULT_CURRENT_CURRENCY, DEFAULT_PERCENT_RATE, DEFAULT_CREDIT_CARD_LIMIT);
    }

    public CreditAccount(int id,
                         double balance,
                         double commission,
                         Currency currentCurrency) {
        this(id, balance, commission, currentCurrency, DEFAULT_PERCENT_RATE, DEFAULT_CREDIT_CARD_LIMIT);
    }

    public CreditAccount(int id,
                         double balance,
                         double commission,
                         Currency currentCurrency,
                         double percentRate,
                         double creditCardLimit) {
        super(id, balance, commission, currentCurrency);
        this.percentRate = percentRate;
        this.creditCardLimit = creditCardLimit;
    }

    // процентная ставка
    public double getPercentRate() {
        return percentRate;
    }

    public void setPercentRate(double percentRate) {
        this.percentRate = percentRate;
    }

    // лимит по кредитной карте
    public double getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(double creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }

    // начисленные проценты
    public double getAssessedPercent() {
        return assessedPercent;
    }

    public void setAssessedPercent(double assessedPercent) {
        this.assessedPercent = assessedPercent;
    }

    // начисленные комиссионные
    public double getAssessedCommission() {
        return assessedCommission;
    }

    public void setAssessedCommission(double assessedComission) {
        this.assessedCommission = assessedComission;
    }

    // метод начисления процентов
    public void accrualPercent() {
        if (getBalance() < getCreditCardLimit()) {
            assessedPercent += (creditCardLimit - getBalance()) * (percentRate / actualDaysOfYear() / 100.0);
        } else {
            assessedPercent += (getBalance() * percentRate) / 100;
        }
    }

    // метод, вычитающий комиссию из остатка
    @Override
    public void commissionFromBalance() throws InsufficientFundsException {
        if (getCommission() > getBalance()) {
            throw new InsufficientFundsException();
        }
        balance -= getCommission();
    }

    // метод пополнения счета
    @Override
    public void addToBalance(double value) throws InsufficientFundsException {
        if (value > creditCardLimit) {
            throw new InsufficientFundsException();
        }
        value = residueFromRepayAssessedCommission(value);
        if (value > 0) {
            value = residueFromRepayAssessedPercent(value);
        }
        if (value > 0) {
            balance += Recount.doubleToLong(value);
        }
    }

    private int actualDaysOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
    }

    private double residueFromRepayAssessedCommission(double value) {
        if (assessedCommission == value) {
            assessedCommission = 0;
            return 0.0;
        } else if (assessedCommission > value) {
            assessedCommission -= value;
            return 0.0;
        } else {
            value -= assessedCommission;
            assessedCommission = 0;
            return value;
        }
    }

    private double residueFromRepayAssessedPercent(double value) {
        if (assessedPercent == value) {
            assessedPercent = 0;
            return 0.0;
        } else if (assessedPercent > value) {
            assessedPercent -= value;
            return 0.0;
        } else {
            value -= assessedPercent;
            assessedPercent = 0;
            return value;
        }

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreditAccount that = (CreditAccount) o;
        return Double.compare(that.percentRate, percentRate) == 0 &&
                Double.compare(that.creditCardLimit, creditCardLimit) == 0 &&
                Double.compare(that.assessedPercent, assessedPercent) == 0 &&
                Double.compare(that.assessedCommission, assessedCommission) == 0 &&
                that.getCurrentCurrency() == getCurrentCurrency() &&
                that.getId() == getId() &&
                that.getCommission() == getCommission() &&
                that.getBalance() == getBalance();

    }

    @Override
    public int hashCode() {
        return 111_111 ^
                Double.hashCode(getBalance()) ^
                Double.hashCode(getPercentRate()) ^
                Double.hashCode(getCreditCardLimit()) ^
                Double.hashCode(getAssessedCommission()) ^
                Double.hashCode(getAssessedPercent()) ^
                Double.hashCode(getCommission()) ^
                getCurrentCurrency().hashCode() ^
                getId();

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreditAccount{");
        sb.append("id=").append(getId());
        sb.append(", balance=").append(getBalance());
        sb.append(", currentCurrency=").append(getCurrentCurrency());
        sb.append(", Commission=").append(getCommission());
        sb.append(", percentRate=").append(percentRate);
        sb.append(", creditСardLimit=").append(creditCardLimit);
        sb.append(", assessedPercent=").append(assessedPercent);
        sb.append(", assessedCommission=").append(assessedCommission);

        sb.append('}');
        return sb.toString();
    }
}
