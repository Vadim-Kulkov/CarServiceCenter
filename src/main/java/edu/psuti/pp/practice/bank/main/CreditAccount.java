/*
 * Copyright (c) PSUTI_PP, IVT-02
 * Kulkov
 */

package edu.psuti.pp.practice.bank.main;

import edu.psuti.pp.practice.bank.exceptions.InsufficientFundsException;
import edu.psuti.pp.practice.bank.service.Converter;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class CreditAccount extends Account {

    private static final double DEFAULT_PERCENT_RATE = 10.0;
    private static final double DEFAULT_CREDIT_CARD_LIMIT = 100_000;


    private double percentRate;

    private double creditCardLimit;

    private double assessedPercent;

    private double assessedCommission;

    public CreditAccount(int id) {
        this(id, DEFAULT_BALANCE, DEFAULT_COMMISSION, DEFAULT_CURRENCY, DEFAULT_PERCENT_RATE, DEFAULT_CREDIT_CARD_LIMIT);
    }

    public CreditAccount(int id, double balance) {
        this(id, balance, DEFAULT_COMMISSION, DEFAULT_CURRENCY, DEFAULT_PERCENT_RATE, DEFAULT_CREDIT_CARD_LIMIT);
    }

    public CreditAccount(int id, double balance, double commission) {
        this(id, balance, commission, DEFAULT_CURRENCY, DEFAULT_PERCENT_RATE, DEFAULT_CREDIT_CARD_LIMIT);
    }

    public CreditAccount(int id, double balance, double commission, Currency currency) {
        this(id, balance, commission, currency, DEFAULT_PERCENT_RATE, DEFAULT_CREDIT_CARD_LIMIT);
    }

    public CreditAccount(int id, double balance,
                         double commission,
                         Currency currentCurrency,
                         double percentRate,
                         double creditCardLimit) {
        super(id, balance, commission, currentCurrency);
        this.percentRate = percentRate;
        this.creditCardLimit = creditCardLimit;
    }

    public double getPercentRate() {
        return percentRate;
    }

    public void setPercentRate(double percentRate) {
        this.percentRate = percentRate;
    }

    public double getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(double creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }

    public double getAssessedPercent() {
        return assessedPercent;
    }

    public void setAssessedPercent(double assessedPercent) {
        this.assessedPercent = assessedPercent;
    }

    public double getAssessedCommission() {
        return assessedCommission;
    }

    public void setAssessedCommission(double assessedComission) {
        this.assessedCommission = assessedComission;
    }

    public void accruePercent() {
        if (getBalance() < getCreditCardLimit()) {
            assessedPercent += (creditCardLimit - getBalance()) * (percentRate / getActualDaysOfYear() / 100);
        } else {
            assessedPercent += (getBalance() * percentRate) / 100;
        }
    }

    @Override
    public void debitCommissionFromBalance() throws InsufficientFundsException {
        if (getCommission() > getBalance()) {
            throw new InsufficientFundsException();
        }
        balance -= getCommission();
    }

    @Override
    public void addToBalance(double value) throws InsufficientFundsException {
        if (value > creditCardLimit) {
            throw new InsufficientFundsException();
        }
        value = repayAssessedCommissionByValue(value);
        if (isValueMoreThanZero(value)) {
            value = repayAssessedPercentByValue(value);
        }
        if (isValueMoreThanZero(value)) {
            balance += Converter.doubleToLong(value);
        }
    }

    private boolean isValueMoreThanZero(double value) {
        return value > 0;
    }

    private int getActualDaysOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
    }

    private double repayAssessedCommissionByValue(double value) {
        if (assessedCommission == value) {
            assessedCommission = 0;
            return 0;
        } else if (assessedCommission > value) {
            assessedCommission -= value;
            return 0;
        } else {
            value -= assessedCommission;
            assessedCommission = 0;
            return value;
        }
    }

    private double repayAssessedPercentByValue(double value) {
        if (assessedPercent == value) {
            assessedPercent = 0;
            return 0;
        } else if (assessedPercent > value) {
            assessedPercent -= value;
            return 0;
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
                that.getCurrency() == getCurrency() &&
                that.getId() == getId() &&
                that.getCommission() == getCommission() &&
                that.getBalance() == getBalance();

    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getBalance(),
                getPercentRate(),
                getCreditCardLimit(),
                getAssessedCommission(),
                getAssessedPercent(),
                getCommission(),
                getCurrency(),
                getId()
        );
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreditAccount{");
        sb.append("percentRate=")
                .append(percentRate)
                .append(", creditCardLimit=")
                .append(creditCardLimit)
                .append(", assessedPercent=")
                .append(assessedPercent)
                .append(", assessedCommission=")
                .append(assessedCommission)
                .append("balance=")
                .append(getBalance())
                .append(", id=")
                .append(getId())
                .append(", commission=")
                .append(getCommission())
                .append(", currency=")
                .append(getCurrency()).append('}');
        return sb.toString();
    }
}
